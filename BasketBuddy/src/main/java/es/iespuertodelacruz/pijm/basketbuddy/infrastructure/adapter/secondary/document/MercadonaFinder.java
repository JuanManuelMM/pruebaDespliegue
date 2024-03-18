package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class MercadonaFinder extends AbstractFinder implements Finder {
	public enum Market {
		  CARREFOUR,
		  ALIMERKA,
		  MASYMAS,
		  DIA,
		  HIPERCOR,
		  EROSKI,
		  MERCADONA,
		  ALDI,
		  GADIS,
		  CONSUM,
		  ALCAMPO
		}
  /**
   * The logger.
   */
  private final Logger logger = LoggerFactory.getLogger(MercadonaFinder.class);

  private final String marketUri =
      "https://7uzjkl1dj0-dsn.algolia.net/1/indexes/products_prod_4315_es/query?x-algolia-application-id=7UZJKL1DJ0&x-algolia-api-key"
          + "=9d8f2e39e90df472b4f2e559a116fe17";

  @Override
  public Market getMarket() {
    return Market.MERCADONA;
  }

  /**
   * Gets the market uri.
   *
   * @return the market uri
   */
  protected String getMarketUri() {
    return this.marketUri;
  }

  /**
   * Gets the request body.
   *
   * @return the post request body
   */
  protected BodyPublisher getBodyPost(final String term) {
    return HttpRequest.BodyPublishers.ofString(
        "{\"params\":\"query=" + term + "&clickAnalytics=true&analyticsTags=%5B%22web%22%5D&getRankingInfo=true\"}");
  }

  /**
   * Gets the http method.
   *
   * @return the http method
   */
  protected HttpMethod getHttpMethod() {
    return HttpMethod.POST;
  }

  /**
   * Gets the product list.
   *
   * @param responseJsonObj the response json obj
   * @return the product list
   */
  
  @Autowired
  ProductoDocumentService productoService;
  
  protected List<Producto> getProductList(final JsonObject responseJsonObj) {

    final List<Producto> productList = new ArrayList<Producto>();

    final JsonArray productsJsonList = responseJsonObj.get("hits").getAsJsonArray();

    if (productsJsonList != null) {
      for (final JsonElement productJson : productsJsonList) {
        final JsonObject productObj = productJson.getAsJsonObject();
        final Producto product = new Producto();
        product.setSupermercado(Market.MERCADONA.toString());
        

        final JsonObject priceObj = productObj.get("price_instructions").getAsJsonObject();

        product.setPrecio((priceObj).get("unit_price").getAsFloat());

        try {
          if (priceObj.get("reference_price") != null && priceObj.get("reference_format") != null) {
            final String price = priceObj.get("reference_price").getAsString().replace(".", ",");
            final String unit = priceObj.get("reference_format").getAsString();
            //product.setPriceUnitOrKg(StringUtils.join(price, " €/", unit));
          }
        } catch (final Exception e) {
          this.logger.error("Mercadona get product unitPrice error", e);
        }

        product.setNombre(productObj.get("display_name").getAsString());
        product.setDescripcion(productObj.get("display_name").getAsString());
        
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        
        for (int i = 0; i < 13; i++) {
            sb.append(rand.nextInt(10)); 
        }

        product.setIdCodigoDeBarras(sb.toString());
        product.setId(sb.toString());
        product.setIdUsuario(1);

        final String imagePath = productObj.get("thumbnail").getAsString();
        if (!StringUtils.isBlank(imagePath)) {
          product.setFoto(imagePath);
        }

        productList.add(product);
        
        List<Producto> all = productoService.findAll();
        boolean productoExistente = all.stream().anyMatch(p -> p.getNombre().equals(product.getNombre()));
        if (!productoExistente) {
          
            productoService.save(product);
        }
        
      }
    }

    return productList;
  }
}

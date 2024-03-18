package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.primary.v2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;
import es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document.Finder;
import es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document.MercadonaFinder;

@RestController
@RequestMapping("api/v2/productosExternos")
public class FindRESTControllerV2 {
	

	    @Autowired
	    private List<Finder> marketFinderList;
	    
	    
	    @Autowired
	    MercadonaFinder finder;
	    
	    /**
	     * Find by id.
	     *
	     * @param term
	     *            the term
	     * @return the list
	     */
	    @CrossOrigin(origins = "*", maxAge = 3600)
	    @GetMapping
	    public List<Producto> findByTerm(@RequestParam(required = false) String term) {

	        final List<Producto> productList = new ArrayList<Producto>();
	        
	        List<Producto> productsByTerm = finder.findProductsByTerm(term);
	        
	        
	        
	        return productsByTerm;
	        
	        /*
	        for (Finder finder : marketFinderList) {
	        	if(markets == null) {
	        		productList.addAll(finder.findProductsByTerm(term));
	        	}
	        }
	        List<Product> finalList = new ArrayList<Product>();
	        term = term.trim();
	        term = StringUtils.stripAccents(term);
	        String[] termSplit = term.toLowerCase().split(" ");
	        for (Product p : productList) {
	            if (Arrays.stream(termSplit).allMatch(StringUtils.stripAccents(p.getName().toLowerCase())::contains)) {
	                finalList.add(p);
	            }
	        }

	        Collections.sort(finalList);

	        return finalList;
	        */
	    }
	}


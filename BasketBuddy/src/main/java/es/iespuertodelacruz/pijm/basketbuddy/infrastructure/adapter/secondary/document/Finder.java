package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document;

import java.util.List;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;
import es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document.MercadonaFinder.Market;



/**
 * The Interface Finder.
 */
public interface Finder {

    /**
     * Find products by term.
     *
     * @param term
     *            the term
     * @return the list
     */
    public List<Producto> findProductsByTerm(String term);
    
    /**
     * Get the finder market.
     *
     * @return the market
     */
    public Market getMarket();

}

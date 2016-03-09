/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.semantic;

import ca.nines.ise.dom.DOM;
import java.io.IOException;
import org.atteo.classindex.ClassIndex;
import org.atteo.classindex.IndexSubclasses;

/**
 * Semantic validators express constraints that don't fit cleanly in a schema. 
 * 
 * Subclasses are automatically indexed, and will be called in validate(). 
 *
 * @author michael
 */
@IndexSubclasses
abstract public class SemanticValidator {
    
    /**
     * Validate a DOM.
     * 
     * @param dom
     * @throws IOException 
     */
    abstract public void validateDOM(DOM dom) throws IOException;

    /**
     * Validate a DOM against all of the semantic validators.
     * 
     * @param dom
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException 
     */
    public static final void validate(DOM dom) throws IOException, IllegalAccessException, InstantiationException {
        for(Class<? extends SemanticValidator> cls : ClassIndex.getSubclasses(SemanticValidator.class)) {
            SemanticValidator v = (SemanticValidator) cls.newInstance();
            v.validateDOM(dom);
        }
    }
    
}

/**
 * 
 */
package ca.csf.dfc.poo.interfaces;

import java.io.FileNotFoundException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

/**
 * @author Maximilian
 *
 */
public interface IImportShapes {
	
	public void create() throws FileNotFoundException, XMLStreamException, FactoryConfigurationError;

}

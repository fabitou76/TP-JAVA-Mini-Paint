package ca.csf.dfc.poo.classes;

/**
 * Classe représentant un rectangle avec un point initial et un point final
 *  
 */

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

import ca.csf.dfc.poo.interfaces.IFormatDataAndSave;
import ca.csf.dfc.poo.interfaces.IGenerateShapes;

public class Rectangle extends Shape{
	
	/**
	 * Constructeur par défaut
	 */
	public Rectangle() {
		this(null,null);
	}
	
	/**
	 * Constructeur d'initialisation
	 * @param 1 p_initialPoint point initial de le rectangle
	 * @param 2 p_finalPoint point final de le rectangle
	 * 
	 */
	public Rectangle(Point p_initialPoint, Point p_finalPoint) {
		super.setIntialPoint(p_initialPoint);
		super.setFinalPoint(p_finalPoint);
		super.setName("rectangle");
	}
	
	/**
	 * Pour exporter une image avec le format xml
	 * @param p_export 
	 * 					le type de formatage a exporter 
	 * @throws XMLStreamException si jamais l'exportation ne marche pas
	 */
	@Override
	public void export(IFormatDataAndSave p_export) throws XMLStreamException {
		p_export.exportRectangle(this);
		
	}
	
	/**
	 * Pour exporter une image avec le format xml
	 * @param p_import
	 * 					le type de formatage a importer
	 * @throws XMLStreamException si jamais l'importation ne marche pas
	 */
	@Override
	public void importData(IGenerateShapes p_import) throws XMLStreamException {
		p_import.importDataRectangle(this);
		
	}
	
	
	
}

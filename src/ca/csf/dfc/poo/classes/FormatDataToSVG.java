/**
 * 
 */
package ca.csf.dfc.poo.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import ca.csf.dfc.poo.interfaces.IFormatDataAndSave;

/**
 * Classe initiant le processus d'�criture en SVG des formes stock�es en m�moires / sur l'�cran
 * @author Maximilian
 *
 */
public class FormatDataToSVG implements IFormatDataAndSave {
	
	// Donn�es membres
	private XMLStreamWriter m_svgDoc;
	
	private ArrayList<Shape> m_listOfShapes = null; 
	
	/**
	 * constructeur
	 * @throws XMLStreamException 
	 * @throws IOException 
	 * @param contient la liste des formes � parcourir pour en retirer les donn�es et les mettre en format SVG
	 */
	
	public  FormatDataToSVG(ArrayList<Shape>p_ListOfShapes) {
		this.m_listOfShapes = p_ListOfShapes;
	}
	
	/**
	 * M�thode v�rifiant si le fichier s�lectionn� par l'utilisateur a ou non une extention XML. La rajoute sinon
	 */
	@Override
	public String checkFileExtension(String p_folderName) {
		p_folderName = !p_folderName.endsWith(".xml") ? p_folderName += ".xml" :p_folderName;
		return p_folderName;
	}

	/**
	 * r�cup�re de la liste des formes les donn�es de chaque forme pour en cr�er une version SVG
	 */
	@Override
	public void formatAndSave(String p_folderName) throws IOException, XMLStreamException, FactoryConfigurationError {
			p_folderName = checkFileExtension(p_folderName);
			
			FileWriter outputWriter = new FileWriter(new File(p_folderName));
			this.m_svgDoc = XMLOutputFactory.newInstance().createXMLStreamWriter(outputWriter);
//			format.setXMLStreamWriter(this.m_svgDoc);
			
			// DEBUT ECRITURE DOCUMENT SVG
			this.m_svgDoc.writeStartDocument();
			this.m_svgDoc.writeStartElement("svg");
			this.m_svgDoc.writeAttribute("xmlns", "https://www.w3.org/2000/svg");
			this.m_svgDoc.writeAttribute("version","1.1");
			this.m_svgDoc.writeAttribute("Width","à définir");
			this.m_svgDoc.writeAttribute("height","à définir");
					
			this.m_svgDoc.writeStartElement("title");
			this.m_svgDoc.writeEndElement();//title
			this.m_svgDoc.writeStartElement("desc");
						
			this.m_svgDoc.writeEndElement();//desc
					
					for (Shape oneShape : this.m_listOfShapes) {
						oneShape.export(this);
					}
					
					this.m_svgDoc.writeEndElement();
					this.m_svgDoc.writeEndDocument();
			
					this.m_svgDoc.flush();
					this.m_svgDoc.close();
					this.m_svgDoc = null;
		}
		
	/**
	 * m�thode pour r�cupp�rer la liste des formes
	 */
	@Override
	public ArrayList<Shape> getShapeList() {
		return this.m_listOfShapes;
	}
	
	/**
	 * m�thode r�cup�rant les donn�es d'un rectangle pour les convertir en format SVG et les �crire sur le disque local  
	 */
	@Override
	public void exportRectangle(Rectangle p_Rectangle) throws XMLStreamException {
		// GENERATION DES VARIABLES UTILES
				String width = Integer.toString(p_Rectangle.getWidth());
				String height= Integer.toString(p_Rectangle.getHeight());
				String x = Integer.toString(p_Rectangle.getFinalPoint().x);
				String y = Integer.toString(p_Rectangle.getFinalPoint().y);
				String fill = p_Rectangle.getFillColor() == null ? "": p_Rectangle.getFillColor().toString() ;
				String stroke = p_Rectangle.getBorderColor().toString();
				String strokeWidth = Integer.toString(p_Rectangle.getBorderWidth());
				 
				
				this.m_svgDoc.writeStartElement("rect");
				this.m_svgDoc.writeAttribute("width", width);
				this.m_svgDoc.writeAttribute("height", height);
				this.m_svgDoc.writeAttribute("x", x);
				this.m_svgDoc.writeAttribute("y", y);
				this.m_svgDoc.writeAttribute("fill", fill);
				this.m_svgDoc.writeAttribute("stroke", stroke);
				this.m_svgDoc.writeAttribute("stroke-width", strokeWidth);
				this.m_svgDoc.writeEndElement();
		
	}
	
	/**
	 * m�thode r�cup�rant les donn�es d'une ligne pour les convertir en format SVG et les �crire sur le disque local  
	 */
	@Override
	public void exportLine(Line p_Line) throws XMLStreamException {
		// GENERATION DES VARIABLES UTILES
		String x1 = Integer.toString(p_Line.getInitialPoint().x);
		String y1 = Integer.toString(p_Line.getInitialPoint().y);
		String x2 = Integer.toString(p_Line.getFinalPoint().x);
		String y2 = Integer.toString(p_Line.getFinalPoint().y);
		String stroke = p_Line.getFillColor().toString();
		String fill = p_Line.getFillColor() == null ? "": p_Line.getFillColor().toString() ;
		String strokeWidth = Integer.toString(p_Line.getBorderWidth());
		 
		
		this.m_svgDoc.writeStartElement("line");
		this.m_svgDoc.writeAttribute("x1", x1);
		this.m_svgDoc.writeAttribute("y1", x2);
		this.m_svgDoc.writeAttribute("x2", y1);
		this.m_svgDoc.writeAttribute("y2", y2);
		this.m_svgDoc.writeAttribute("stroke", stroke);
		this.m_svgDoc.writeAttribute("fill", fill);
		this.m_svgDoc.writeAttribute("stroke-width", strokeWidth);
		this.m_svgDoc.writeEndElement();
	}
	
	/**
	 * m�thode r�cup�rant les donn�es d'une ellipse pour les convertir en format SVG et les �crire sur le disque local  
	 */
	@Override
	public void exportEllipse(Elipse p_Ellipse) throws XMLStreamException {
		 String rx = Integer.toString((p_Ellipse.getFinalPoint().x) - (p_Ellipse.getInitialPoint().x));
		 String ry = Integer.toString((p_Ellipse.getFinalPoint().y) - (p_Ellipse.getInitialPoint().y));
		 String cx = Integer.toString((p_Ellipse.getFinalPoint().x) - (p_Ellipse.getInitialPoint().x) / 2);
		 String cy =  Integer.toString((p_Ellipse.getFinalPoint().y) - (p_Ellipse.getInitialPoint().y) / 2);
		 String fill = p_Ellipse.getFillColor() == null ? "": p_Ellipse.getFillColor().toString() ;
		 String stroke = p_Ellipse.getBorderColor().toString();
		 String strokeWidth = Integer.toString(p_Ellipse.getBorderWidth());
		 
		 this.m_svgDoc.writeStartElement("ellipse");
		 this.m_svgDoc.writeAttribute("cx", cx);
		 this.m_svgDoc.writeAttribute("cy", cy);
		 this.m_svgDoc.writeAttribute("rx", rx);
		 this.m_svgDoc.writeAttribute("ry", ry);
		 this.m_svgDoc.writeAttribute("fill", fill);
		 this.m_svgDoc.writeAttribute("stroke-width", strokeWidth);
		 this.m_svgDoc.writeAttribute("stroke", stroke);
		 this.m_svgDoc.writeEndElement();
		
	}

	
	

}

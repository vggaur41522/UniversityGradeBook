/*==================================================================
*  IMPORTANT        : File for academic project for course CSE564
*  Category         : Academic project
*  Type             : Java Program
*  Description      : Code for project 2- CSE564
*  Author           : Varun Gaur
*  Modification Log :
*  Author           Date		Reason
*  Varun Gaur       2-Apr-2016		Original
* =================================================================*/
package gradeBookClient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Student" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="GradeItem" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>byte">
 *                           &lt;attribute name="ItemName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="feedback" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "student"
})
@XmlRootElement(name = "GradeBook")
public class GradeBook {

    @XmlElement(name = "Student")
    protected List<GradeBook.Student> student;

    /**
     * Gets the value of the student property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the student property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStudent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GradeBook.Student }
     * 
     * 
     */
    public List<GradeBook.Student> getStudent() {
        if (student == null) {
            student = new ArrayList<GradeBook.Student>();
        }
        return this.student;
    }
    
    public  void setStudent(List<GradeBook.Student> student) {
        this.student = student;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "gradeItem"
    })
    public static class Student {

        @XmlElement(name = "GradeItem")
        protected List<GradeBook.Student.GradeItem> gradeItem;
        @XmlAttribute(name = "id")
        protected int id;

        /**
         * Gets the value of the gradeItem property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the gradeItem property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGradeItem().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GradeBook.Student.GradeItem }
         * 
         * 
         */
        public List<GradeBook.Student.GradeItem> getGradeItem() {
            if (gradeItem == null) {
                gradeItem = new ArrayList<GradeBook.Student.GradeItem>();
            }
            return this.gradeItem;
        }
        
        
        public void setGradeItem(List<GradeBook.Student.GradeItem> gradeItem) {
            
            this.gradeItem = gradeItem;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link Byte }
         *     
         */
        public int getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link Byte }
         *     
         */
        public void setId(int value) {
            this.id = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>byte">
         *       &lt;attribute name="ItemName" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}byte" />
         *       &lt;attribute name="feedback" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class GradeItem {

            @XmlValue
            protected int value;
            @XmlAttribute(name = "ItemName")
            protected String itemName;
            @XmlAttribute(name = "weight")
            protected int weight;
            @XmlAttribute(name = "feedback")
            protected String feedback;
            @XmlAttribute(name = "appealText")
            protected String appealText;

            /**
             * Gets the value of the value property.
             * 
             */
            public int getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             */
            public void setValue(int value) {
                this.value = value;
            }

            /**
             * Gets the value of the itemName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getItemName() {
                return itemName;
            }

            /**
             * Sets the value of the itemName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setItemName(String value) {
                this.itemName = value;
            }

            /**
             * Gets the value of the weight property.
             * 
             * @return
             *     possible object is
             *     {@link Byte }
             *     
             */
            public int getWeight() {
                return weight;
            }

            /**
             * Sets the value of the weight property.
             * 
             * @param value
             *     allowed object is
             *     {@link Byte }
             *     
             */
            public void setWeight(int value) {
                this.weight = value;
            }

            /**
             * Gets the value of the feedback property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFeedback() {
                return feedback;
            }

            /**
             * Sets the value of the feedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFeedback(String value) {
                this.feedback = value;
            }

            // New Item Appeal TEXT
            /**
             * Gets the value of the feedback property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getappealText() {
                return appealText;
            }

            /**
             * Sets the value of the feedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setappealText(String value) {
                this.appealText = value;
            }
            
        }

    }

}

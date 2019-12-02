package CapaLogica;

import CapaDatos.BoticasData;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.List;
import entidad.Venta;
import CapaDatos.TipoMovimientoData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import entidad.CABECERA;
import java.util.Arrays;
import java.util.Enumeration;
import java.io.*;
import org.xml.sax.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyException;
import java.util.Collections;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.Document;


import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.*;
import javax.xml.crypto.dsig.spec.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


//import org.jdom.Document;		// |
//import org.jdom.Element;		// |\ Librerias
//import org.jdom.JDOMException;		// |/ JDOM
//import org.jdom.input.SAXBuilder;	// |


public class XML_GENERAR {

    BoticasData objBoticas;    
    private List<CABECERA> listaCABECERA = new ArrayList<CABECERA>();
    TipoMovimientoData objTipoMovimiento;
    BOLETA objBOLETA;
    FACTURA objFACTURA;
    NOTA_CREDITO objNOTACREDITO;
    NOTA_DEBITO objNOTADEBITO;
    String NuevaRutaXML = "";   
    String RUC = "20399497257";
    String Codificacion = "ISO-8859-1";
    String Password = "ZAtaM6LKJ5qm45mM";
    
    /*String RutaBase ="D:\\XML\\";
    String RutaTMP = "D:\\XML\\TMP\\";
    String pkcs12CertificateFilePath = "D:\\SISBOTI.API\\bin\\Debug\\CERTIFICADO\\NORTFARMA_SAC.p12\\";    
    String signedFileSavePath="D:\\FE_GINO\\Boleta_firmada_";
    */
    
    String pkcs12CertificateFilePath ="/mnt/sistema/NORTFARMA_SAC.p12";
    String RutaBase ="/mnt/XML/";
    String RutaTMP = "/mnt/XML/TMP/";
    String signedFileSavePath="";
    
    
    FileOutputStream fos = null;

    public XML_GENERAR() {
        objBoticas = new BoticasData();
    }

    public void Execute(List<Venta> listaVta) throws IOException, Exception{         

        CABECERA objresultado = null;
        objBOLETA = new BOLETA();
        objFACTURA = new FACTURA();
        objNOTACREDITO = new NOTA_CREDITO();
        objNOTADEBITO = new NOTA_DEBITO();

        int TipoDoc = Integer.valueOf(listaVta.get(0).getId_Tipo_Venta());
        String id_venta = listaVta.get(0).getId_Venta();
        String id_botica = listaVta.get(0).getId_Botica();
        Date MyFechaVenta = new Date();
        DateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
        formatoFecha.format(MyFechaVenta);

        NuevaRutaXML = CreaDirectorio(formatoFecha.format(MyFechaVenta), listaVta.get(0).getId_Botica().toString());

        String StrTipoDoc = listaVta.get(0).getidtipdocsunat().toString();
        String StrSerie = listaVta.get(0).getSerie().toString();
        String StrNumero = listaVta.get(0).getnumerFE().toString();
        String MyFile = RUC + '-' + StrTipoDoc + '-' + StrSerie + '-' + StrNumero;
        String FileXML = MyFile + ".XML";
        String FileZip = MyFile + ".ZIP";
        String TMPXML = RutaTMP + FileXML;
        String MyXML ="";

        //listaCABECERA = objBOLETA.GeneraXML(id_venta);
        if (TipoDoc == 11){ MyXML = objBOLETA.GeneraXML(id_venta,id_botica); }
        if (TipoDoc == 12){ MyXML = objFACTURA.GeneraXML(id_venta,id_botica); }
        if (TipoDoc == 13){ MyXML = objNOTACREDITO.GeneraXML(id_venta,id_botica); }
        if (TipoDoc == 14){ MyXML = objNOTACREDITO.GeneraXML(id_venta,id_botica); }
        if (TipoDoc == 15){ MyXML = objNOTADEBITO.GeneraXML(id_venta,id_botica); }
        if (TipoDoc == 16){ MyXML = objNOTADEBITO.GeneraXML(id_venta,id_botica); }

        
        FileWriter escribir=new FileWriter(NuevaRutaXML + FileXML,true);
        escribir.write(MyXML);
        escribir.close();
        
       
        List<Boolean> CHK_SIGNATURE = Firma(NuevaRutaXML, FileXML, StrSerie + "-" + StrNumero,id_venta);
        //List<Boolean> CHK_SIGNATURE = FirmaDigital(NuevaRutaXML, FileXML, StrSerie + "-" + StrNumero);        

    }
    
    private static void writeFile(File file,String MyXML) throws IOException{
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter (fw);
        PrintWriter writer = new PrintWriter (bw);

        writer.println(MyXML);
        writer.close();
    }

    public String CreaDirectorio(String FechaVenta, String MyBotica) throws FileNotFoundException
    {
        NuevaRutaXML = RutaBase + FechaVenta + "/" + MyBotica + "/";

        File Ffichero = new File(RutaBase);
            if (!Ffichero.exists()) {
                Ffichero.mkdir();
            }

        File Ffichero1 = new File(RutaBase + FechaVenta + "/");
            if (!Ffichero1.exists()) {
                Ffichero1.mkdir();
        }

        File Ffichero2 = new File(RutaBase + FechaVenta + "/"+ MyBotica + "/");
            if (!Ffichero2.exists()) {
                   Ffichero2.mkdir();
        }

        
        /*NuevaRutaXML = RutaBase + FechaVenta + "/" + MyBotica + "/";

        try {
            File Ffichero = new File(RutaBase);
            if (!Ffichero.exists()) {
                Ffichero.mkdir();
            }
            //fos = new FileOutputStream(NuevaRutaXML);
        } catch (Exception ex) {
            System.out.println(ex);
            try {
                File Ffichero = new File("C:\\XML\\" + FechaVenta + "\\" + MyBotica + "\\");
                if (!Ffichero.exists()) {
                    Ffichero.mkdirs();
                }
                //fos = new FileOutputStream("C:\\XML\\" + FechaVenta + "\\" + MyBotica + "\\");
            } catch (Exception ex1) {
                //Logger.getLogger(Descargos.class.getName()).log(Level.SEVERE, null, ex1);
                System.out.println("CARGO DBF." + ex1.getMessage());
            }
        }*/

     return NuevaRutaXML;
    }

    List<Boolean> FirmaDigital(String RutaXML, String FileXML, String SignatureID) throws IOException, Exception    
    {
        List<Boolean> Retorno=new ArrayList<Boolean>(Arrays.asList(new Boolean[2]));
      
        String l_xml = RutaXML + FileXML;

        XMLSignatureFactory fac = getXMLSignatureFactory();
        Reference ref = getSHA1WholeDocumentEnvelopedTransformReference(fac);
        //SignedInfo si = getSignedInfo(fac, ref);
        //PrivateKeyEntry keyEntry = loadPKCS12KeyStoreAndGetSigningKeyEntry(pkcs12CertificateFilePath, Password);
        //KeyInfo ki = getKeyInfoWithX509Data(keyEntry, fac);
        //Document doc = instantiateDocumentToBeSigned(l_xml);
        //signDocumentAndPlaceSignatureAsFirstChildElement(doc, keyEntry, fac, si, ki);
        //writeResultingDocument(doc,signedFileSavePath);
            
        return Retorno;
    }
    private static XMLSignatureFactory getXMLSignatureFactory() {
        return XMLSignatureFactory.getInstance("DOM");
    }
    private static Reference getSHA1WholeDocumentEnvelopedTransformReference(XMLSignatureFactory fac) throws Exception {
        return
            fac.newReference(
                "",
                fac.newDigestMethod(DigestMethod.SHA1, null),
                Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
                null,
                null
            );
    }

    /*private static SignedInfo getSignedInfo(XMLSignatureFactory fac, Reference ref) throws Exception {
        return
            fac.newSignedInfo(
                fac.newCanonicalizationMethod(
                    CanonicalizationMethod.INCLUSIVE,
                    (C14NMethodParameterSpec) null
                ),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref)
            );
    }*/

    /*private static PrivateKeyEntry loadPKCS12KeyStoreAndGetSigningKeyEntry(String pkcs12CertificateFilePath, String password) throws Exception {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(pkcs12CertificateFilePath), password.toCharArray());
        return (PrivateKeyEntry)ks.getEntry(ks.aliases().nextElement(), new KeyStore.PasswordProtection(password.toCharArray()));
    }*/

    /*private static KeyInfo getKeyInfoWithX509Data(PrivateKeyEntry keyEntry, XMLSignatureFactory fac) {
        X509Certificate cert = (X509Certificate) keyEntry.getCertificate();
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        List x509Content = new ArrayList();
        x509Content.add(cert.getSubjectX500Principal().getName());
        x509Content.add(cert);
        X509Data xd = kif.newX509Data(x509Content);
        return kif.newKeyInfo(Collections.singletonList(xd));
    }*/

    /*private static Document instantiateDocumentToBeSigned(String fileToBeSignedPath) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        return dbf.newDocumentBuilder().parse(new FileInputStream(fileToBeSignedPath));
    }*/

    /*private static void signDocumentAndPlaceSignatureAsFirstChildElement(Document doc, PrivateKeyEntry keyEntry, XMLSignatureFactory fac, SignedInfo si, KeyInfo ki) throws Exception {
        DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement(), doc.getDocumentElement().getOwnerDocument().getFirstChild());
        XMLSignature signature = fac.newXMLSignature(si, ki);
        signature.sign(dsc);
    }*/

    /*private static void writeResultingDocument(Document doc, String signedFileSavePath) throws Exception {
        OutputStream os = new FileOutputStream(signedFileSavePath);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));
    }*/

 /*******************************************************************************************************************/

    List<Boolean> Firma(String RutaXML, String FileXML, String SignatureID,String idventa) throws IOException
    //private List<Object> Firma(String RutaXML, String FileXML, String SignatureID)
    {
        List<Boolean> Retorno=new ArrayList<Boolean>(Arrays.asList(new Boolean[2]));

        String l_xml = RutaXML + FileXML;
        String l_xpath;

       try {

            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
            Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null),
            Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
            null,null);


            SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
            (C14NMethodParameterSpec)null),
             fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
             Collections.singletonList(ref));

            KeyStore ks = KeyStore.getInstance("pkcs12");
            ks.load(new FileInputStream(pkcs12CertificateFilePath), Password.toCharArray());

            Enumeration<String> en = ks.aliases();
            String alias = (String) en.nextElement();

            KeyStore.PrivateKeyEntry keyEntry
                    = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(Password.toCharArray()));

            X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

            KeyInfoFactory kif = fac.getKeyInfoFactory();
            List x509Content = new ArrayList();
            x509Content.add(cert.getSubjectX500Principal().getName());
            x509Content.add(cert);
            X509Data xd = kif.newX509Data(x509Content);
            KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(l_xml));            

            DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(),doc.getDocumentElement());
            XMLSignature signature = fac.newXMLSignature(si, ki);
            //XMLSignature signature = fac.newXMLSignature(si, ki,null,"SignST",null);
            signature.sign(dsc);

            /*String nombre="";
            NodeList nodes = doc.getElementsByTagName("DigestValue");
            Node nodo = nodes.item(0);

            if (nodo instanceof Element){

               Node primerHijo = nodo.getFirstChild();
               Node siguiente = primerHijo.getNextSibling();
               nombre = nodo.getNodeName();
               String contenido = nodo.getTextContent();


            System.out.println(primerHijo);
            System.out.println(siguiente);
            System.out.println(nombre);
            System.out.println(contenido);
            
            //if (nombre.equals("ext:ExtensionContent")) {
            //}
            }
            */
            //OutputStream os = new FileOutputStream(signedFileSavePath+idventa+".xml");
            OutputStream os = new FileOutputStream(l_xml);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(os));
            //readXML();


               fac=null;
               ref=null;
               si=null;
               os=null;
               tf=null;
               dsc=null;
               doc=null;
               kif=null;
               xd=null;
               ki=null;
               ks=null;
               cert=null;


        } catch (IOException e) {
                e.printStackTrace();
		return null;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
        finally{
         
        }
        return Retorno;

    }

    public String readXML(String miinterno,String LeerNuevaRutaXML, String LeerMyFile) throws ParserConfigurationException, SAXException, IOException {
        //signedFileSavePath = LeerNuevaRutaXML+LeerMyFile;
        //System.out.println(LeerNuevaRutaXML+LeerMyFile);
        String contenido="";

        try{
        
        File myfile = new File(LeerNuevaRutaXML+LeerMyFile+".XML");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document mydoc = db.parse(myfile);

        mydoc.getDocumentElement().normalize();

        NodeList nlist = mydoc.getElementsByTagName("DigestValue");
        Node nodo = nlist.item(0);
        myfile=null;

        /*for(int i = 0; i< nlist.getLength(); i++){
        Node nod = nlist.item(i);
            if(nod.getNodeType() == Node.ELEMENT_NODE){
                Element e = (Element) nod;
                System.out.println(getTagValue("ext:UBLExtensions",e));
                System.out.println(getTagValue("ext:ExtensionContent",e));
                System.out.println(getTagValue("sac:AdditionalInformation",e));
                System.out.println(getTagValue("sac:AdditionalMonetaryTotal",e));
                System.out.println(getTagValue("cbc:ID",e));
            }
        }*/        
        //NodeList nodeList = mydoc.getDocumentElement().getChildNodes();
        //for (int i = 0; i < nodeList.getLength(); i++) {
        //  Node node = nodeList.item(i);

        Node primerHijo = nodo.getFirstChild();         
        contenido = nodo.getTextContent();
        //Node siguiente = primerHijo.getNextSibling();
        //nombre = nodo.getNodeName();
        //}
        return contenido;

        } catch (FileNotFoundException ex) {
            System.out.println("Error archivo no encontrado" + ex.getMessage());
            contenido="";
            return contenido;
        }
        
    }

    public String readXMLSignature(String miinterno,String LeerNuevaRutaXML, String LeerMyFile) throws ParserConfigurationException, SAXException, IOException {

        String contenido1="";

        try{

        File myfile = new File(LeerNuevaRutaXML+LeerMyFile+".XML");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document mydoc = db.parse(myfile);

        mydoc.getDocumentElement().normalize();

        NodeList nlist = mydoc.getElementsByTagName("SignatureValue");
        Node nodo = nlist.item(0);
        myfile=null;

        Node primerHijo = nodo.getFirstChild();
        contenido1 = nodo.getTextContent();

        return contenido1;

        } catch (FileNotFoundException ex) {
            System.out.println("Error archivo no encontrado" + ex.getMessage());
            contenido1="";
            return contenido1;
        }

    }
    
    public String getTagValue(String nom, Element e){
        NodeList lst = e.getElementsByTagName(nom).item(0).getChildNodes();
        Node n = (Node) lst.item(0);
        return n.getNodeValue();
    }
 
    
   
}

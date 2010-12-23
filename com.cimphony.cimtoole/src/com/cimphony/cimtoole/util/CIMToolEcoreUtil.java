package com.cimphony.cimtoole.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.omg.CORBA.INITIALIZE;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

public class CIMToolEcoreUtil {
	
	private static Map<Resource, Class<?>> XSD_TO_CLASS_MAP = new HashMap<Resource, Class<?>>();
	private static Map<String, Class<?>> XSD_URI_TO_CLASS_MAP = new HashMap<String, Class<?>>();
	private static Map<Class<?>, Resource> CLASS_TO_XSD_MAP = new HashMap<Class<?>, Resource>();
	private static Map<String, EDataType> XSD_URI_TO_EDATATYPE_MAP = new HashMap<String, EDataType>();
	private static boolean INITIALISED = false;
	
	private static void init(){
		XSD_TO_CLASS_MAP.put(XSD.xstring, java.lang.String.class);
		XSD_TO_CLASS_MAP.put(XSD.integer, java.math.BigInteger.class);
		XSD_TO_CLASS_MAP.put(XSD.xint, int.class);
		XSD_TO_CLASS_MAP.put(XSD.xlong, long.class);
		XSD_TO_CLASS_MAP.put(XSD.xshort, short.class);
		XSD_TO_CLASS_MAP.put(XSD.decimal, java.math.BigDecimal.class);
		XSD_TO_CLASS_MAP.put(XSD.xfloat, float.class);
		XSD_TO_CLASS_MAP.put(XSD.xdouble, double.class);
		XSD_TO_CLASS_MAP.put(XSD.xboolean, boolean.class);
		XSD_TO_CLASS_MAP.put(XSD.xbyte, byte.class);
		XSD_TO_CLASS_MAP.put(XSD.QName, javax.xml.namespace.QName.class);
		XSD_TO_CLASS_MAP.put(XSD.dateTime, java.util.Date.class);
		XSD_TO_CLASS_MAP.put(XSD.base64Binary, byte[].class);
		XSD_TO_CLASS_MAP.put(XSD.hexBinary, byte[].class);
		XSD_TO_CLASS_MAP.put(XSD.unsignedInt, long.class);
		XSD_TO_CLASS_MAP.put(XSD.unsignedShort, int.class);
		XSD_TO_CLASS_MAP.put(XSD.unsignedByte, short.class);
		XSD_TO_CLASS_MAP.put(XSD.time, java.util.Date.class);
		XSD_TO_CLASS_MAP.put(XSD.date, java.util.Date.class);
		XSD_TO_CLASS_MAP.put(XSD.gDay, java.util.Date.class);
		XSD_TO_CLASS_MAP.put(XSD.gMonth, java.util.Date.class);
		XSD_TO_CLASS_MAP.put(XSD.gMonthDay, java.util.Date.class);
		XSD_TO_CLASS_MAP.put(XSD.gYear, java.util.Date.class);
		XSD_TO_CLASS_MAP.put(XSD.gYearMonth, java.util.Date.class);
		XSD_TO_CLASS_MAP.put(XSD.anyURI, java.lang.Object.class);
		XSD_TO_CLASS_MAP.put(XSD.duration, javax.xml.datatype.Duration.class);
		XSD_TO_CLASS_MAP.put(XSD.NOTATION, javax.xml.namespace.QName.class);
		
		for (Resource k : XSD_TO_CLASS_MAP.keySet()){
			XSD_URI_TO_CLASS_MAP.put(k.getURI(), XSD_TO_CLASS_MAP.get(k));
			if (!CLASS_TO_XSD_MAP.containsKey(XSD_TO_CLASS_MAP.get(k))){
				CLASS_TO_XSD_MAP.put(XSD_TO_CLASS_MAP.get(k), k);
			}
		}
		
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#string", EcorePackage.eINSTANCE.getEString());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#integer", EcorePackage.eINSTANCE.getEInt());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#int", EcorePackage.eINSTANCE.getEInt());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#long", 	 EcorePackage.eINSTANCE.getELong());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#short",  EcorePackage.eINSTANCE.getEShort());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#decimal",  EcorePackage.eINSTANCE.getEBigDecimal());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#float",  EcorePackage.eINSTANCE.getEFloat());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#double",  EcorePackage.eINSTANCE.getEDouble());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#boolean",  EcorePackage.eINSTANCE.getEBoolean());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#byte",  EcorePackage.eINSTANCE.getEByte());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#QName",  EcorePackage.eINSTANCE.getEString());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#dateTime",  EcorePackage.eINSTANCE.getEDate());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#base64Binary",  EcorePackage.eINSTANCE.getEByteArray());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#hexBinary",  EcorePackage.eINSTANCE.getEByteArray());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#unsignedInt",  EcorePackage.eINSTANCE.getEInt());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#unsignedShort",  EcorePackage.eINSTANCE.getEShort());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#unsignedByte",  EcorePackage.eINSTANCE.getEByte());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#time",  EcorePackage.eINSTANCE.getEDate());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#date",  EcorePackage.eINSTANCE.getEDate());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#g",  EcorePackage.eINSTANCE.getEDate());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#duration",  EcorePackage.eINSTANCE.getEDate());
		XSD_URI_TO_EDATATYPE_MAP.put("http://www.w3.org/2001/XMLSchema#NOTATION",  EcorePackage.eINSTANCE.getEString());
	}
	
	public static Map<String, EDataType> getEDataTypeMap(){
		if (!INITIALISED) init();
		return Collections.unmodifiableMap(XSD_URI_TO_EDATATYPE_MAP);
	}
	
	public static Class<?> getTypeClass(Resource type){
		if (!INITIALISED) init();
		return XSD_TO_CLASS_MAP.get(type);
	}

	public static Class<?> getTypeClass(String type){
		if (!INITIALISED) init();
		return XSD_URI_TO_CLASS_MAP.get(type);
	}
	
	public static Resource getType(Class<?> cls){
		if (!INITIALISED) init();
		return CLASS_TO_XSD_MAP.get(cls);
	}

	
	public static String getDocumentation(EModelElement eModelElement){
		EAnnotation an = eModelElement.getEAnnotation("http://www.eclipse.org/emf/2002/GenModel"); //$NON-NLS-1$
		String text = null;
		if (an != null) {
			if (an.getDetails().containsKey("documentation")) {//$NON-NLS-1$
				text = an.getDetails().get("documentation"); //$NON-NLS-1$
			} else if (an.getDetails().containsKey("Documentation")) { //$NON-NLS-1$
				text = an.getDetails().get("Documentation"); //$NON-NLS-1$
			}
		}
		return text;

	}
}
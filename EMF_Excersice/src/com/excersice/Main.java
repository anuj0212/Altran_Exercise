package com.excersice;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import dart.Asset;
import dart.DartPackage;
import dart.Function;
import dart.Library;
import dart.Package;
import dart.Project;
import dart.impl.DartFactoryImpl;


/**
 *  Loads the dataspec into the model and will read the data and display the information.
 * @author anuj
 *
 */
public class Main {

	public static void main(String[] args) {

		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    Map<String, Object> m = reg.getExtensionToFactoryMap();
	    m.put("dartspec", new XMIResourceFactoryImpl());

	    ResourceSet resSet = new ResourceSetImpl();
	    resSet.getPackageRegistry().put(DartPackage.eNS_URI,
	    		DartPackage.eINSTANCE);
	    Resource resource = resSet.getResource(URI.createURI("resource/dartlang.dartspec"), true);
	    try {
			resource.load(Collections.EMPTY_MAP);
		} catch (IOException e) {
			System.out.println("ERROR : " + e.getMessage());
			e.printStackTrace();
		}
	    System.out.println(" Size of the Resource is : " + resource.getContents().size());

	    EList<EObject> resourceElist = resource.getContents();
	    DataCollector dataInstance = DataCollector.INSTANCE;
	    for (EObject eObject : resourceElist) {
			if(eObject instanceof Project){
				   Project prj = (Project)eObject;
				   System.out.println(" Project --> "+ prj.getName());
				   dataInstance.getPackages(prj);
			}
		}
	    dataInstance.printDetails();
	}

}

package com.excersice;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import dart.Asset;
import dart.Package;
import dart.Project;

public enum DataCollector {

	INSTANCE;

	private static int project_count= 0;
	private static int package_count= 0;
	private static int asset_count= 0;

	void getPackages(Project prj) {
		project_count++;
		 for (Package pckgObj : prj.getPackages()) {
				System.out.println("         Packages --> " + pckgObj.getName());
				package_count++;
				getAssets(pckgObj);
		 }
	}


	private void getAssets(Package pckgObj) {
		EList<Asset> assetsList = pckgObj.getAssets();

		for (Asset asset : assetsList) {
			System.out.println("                  Asset -- > "+asset.getName());
			asset_count++;
			// checking its container
			EList<EObject> assetcontain = asset.eContents();
			assetcontain.stream().forEach(ob -> {
				if(ob instanceof Asset){
					Asset childAsset = (Asset) ob;
					System.out.println("                       Sub -  Asset -- >" + childAsset.getName());
					asset_count++;
				}
				// to add a new Function element..
//				if( ob instanceof Library){
//					System.out.println("instance of library");
//					Library lib = (Library)ob;
//					Function function = DartFactoryImpl.eINSTANCE.createFunction();
//					function.setName("newfunction");
//					function.setBody("newbody");
//					lib.getFunctions().add(function);
//				}
			});

		}
	}


		void printDetails() {

		System.out.println("***********************");
		System.out.println("No. of Project : " + package_count);
		System.out.println("No. of Packages : " + project_count);
		System.out.println("No. of Assets : " + asset_count);

	}

}

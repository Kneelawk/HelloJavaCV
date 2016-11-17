package org.kneelawk.hellojavacv;

import java.io.IOException;

import org.kneelawk.hellojavacv.CPControl3.DirectoryEntryFilter;

public class HelloJavaCVLauncher {

	public static void main(String[] args) {
		CPControl3 cp = new CPControl3("org.kneelawk.hellojavacv.HelloJavaCV");
		cp.addExtractingFromFileLibrary(CPControl3.ME)
				.addLibrary("application", new DirectoryEntryFilter("app"),
						CPControl3.ALWAYS_DELETE)
				.addLibrary("libraries", new DirectoryEntryFilter("libs"),
						CPControl3.ALWAYS_DELETE);
		
		try {
			cp.launch(args);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

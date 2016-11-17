package org.kneelawk.hellojavacv;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

public class HelloJavaCV {

	public static void main(String[] args) {
		Mat m = imread("Hello.png");
		System.out.println(m);
	}

}

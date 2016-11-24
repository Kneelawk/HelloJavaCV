package org.kneelawk.hellojavacv;

import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import java.io.File;
import java.io.IOException;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;

public class HelloJavaCV {

	public static void main(String[] args) {
		String inFile = "images/people-07.png";
		Mat m = imread(inFile);
		File outDir = new File(
				(String) inFile.subSequence(0, inFile.lastIndexOf('.')));
		if (!outDir.exists())
			outDir.mkdirs();

		CascadeClassifier classifier = new CascadeClassifier();
		try {
			classifier
					.load(Loader
							.extractResource("/lbpcascade_frontalface.xml",
									null, "classifier", ".xml")
							.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		RectVector faces = new RectVector();
		classifier.detectMultiScale(m, faces);

		classifier.close();

		long numFaces = faces.size();

		for (long l = 0; l < numFaces; l++) {
			Rect face = faces.get(l);

			int minx = Math.max(face.x() - face.width() / 2, 0);
			int maxx = Math.min(face.x() + face.width() + face.width() / 2,
					m.cols());
			int miny = Math.max(face.y() - face.height() / 2, 0);
			int maxy = Math.min(face.y() + face.height() + face.height() / 2,
					m.rows());
			Mat faceMat = new Mat(m,
					new Rect(new Point(minx, miny), new Point(maxx, maxy)));
			imwrite(outDir.getAbsolutePath() + "/face-" + l + ".png", faceMat);
		}

		for (long l = 0; l < numFaces; l++) {
			Rect face = faces.get(l);

			rectangle(m, face, new Scalar(0.0, 255, 0.0, 255));
		}

		imwrite(outDir.getAbsolutePath() + "/faces.png", m);
		System.out.println("Found faces image written to: "
				+ outDir.getAbsolutePath() + "/faces.png");
	}

}

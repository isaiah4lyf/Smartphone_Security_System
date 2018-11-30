try
	status = 0;
	exception = 0;
	load('webcamsSceneReconstruction.mat');
	image = undistortImage(I1,stereoParams.CameraParameters1);
	faceDetector = vision.CascadeObjectDetector;
	face1 = step(faceDetector,image);
	shiftX = face1(1) + (30/100)*face1(1);
	shiftY = face1(2) + (10/100)*face1(2);
	decX = face1(3) - (30/100)*face1(3);
	decY = face1(4) - (15/100)*face1(4);
	face1 = [shiftX shiftY decX decY];
	TF = isempty(face1);

	if TF == 0
		I = imcrop(I1,face1);
		im=rgb2gray(I);
		J = imresize(im, 5);


		points1 = detectBRISKFeatures(J);
		points2 = detectMSERFeatures(J);
		points3 = detectSURFFeatures(J);
		points4 = detectHarrisFeatures(J);
		points5 = detectKAZEFeatures(J);

		[featuresOriginal1,validPtsOriginal1] = ...
					extractFeatures(J,points1);
		[featuresOriginal2,validPtsOriginal2] = ...
					extractFeatures(J,points2);
		[featuresOriginal3,validPtsOriginal3] = ...
					extractFeatures(J,points3);
		[featuresOriginal4,validPtsOriginal4] = ...
					extractFeatures(J,points4);
		[featuresOriginal5,validPtsOriginal5] = ...
					extractFeatures(J,points5);
					
		featuresOriginal1 = double([featuresOriginal1.Features]);

		featuresOriginal2 = double([featuresOriginal2]);
		
		featuresOriginal3 = double([featuresOriginal3]);
		
		featuresOriginal4 = double([featuresOriginal4.Features]);
		
		featuresOriginal5 = double([featuresOriginal5]);
		


		featuresOriginal = [featuresOriginal1;featuresOriginal2;featuresOriginal3;featuresOriginal4;featuresOriginal5];
		[m n] = size(featuresOriginal);
		
		if m > 850
			featuresOriginal = featuresOriginal(1:850,:);
		end
		
		file = load(path);
	
		trainedClassifier  = file.trainedClassifier;
		
		trained = trainedClassifier.predictFcn(featuresOriginal);
		
	else
		status = 1;
	end	
catch ME
    switch ME.identifier
        case 'MATLAB:UndefinedFunction'
        otherwise
            exception = 1;
			ME
    end
end

file = load(path);
[trainedClassifier, validationAccuracy] = trainClassifier_Badged_Trees3(file.new_File,class_1, class_2, class_3, class_4);
accuracy = validationAccuracy*100

save(path2,'trainedClassifier');

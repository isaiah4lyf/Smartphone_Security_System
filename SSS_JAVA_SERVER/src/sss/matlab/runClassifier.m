file = load(path);
[trainedClassifier, validationAccuracy] = trainClassifierBadgedTrees(file.new_File,class_1, class_2, class_3, class_4);
accuracy = validationAccuracy*100

save(path2,'trainedClassifier');

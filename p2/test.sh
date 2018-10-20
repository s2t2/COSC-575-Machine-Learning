for f in *.mff; do
	echo $f;
	echo "-----------------------------"
	echo "# Naive Bayes #";
	java NaiveBayes -t $f -x 10;
	echo "# iBk #";
	java IBk -t $f -x 10;
	echo "=============================";
done
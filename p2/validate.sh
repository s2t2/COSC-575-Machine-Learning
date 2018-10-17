f1="monks1.tr.mff";
echo $f1;
echo "-----------------------------"
echo "# Naive Bayes #";
java NaiveBayes -t $f1 -T "monks1.te.mff";
echo "# iBk #";
java IBk -t $f1 -T "monks1.te.mff";
echo "=============================";

f2="monks2.tr.mff";
echo $f2;
echo "-----------------------------"
echo "# Naive Bayes #";
java NaiveBayes -t $f2 -T "monks2.te.mff";
echo "# iBk #";
java IBk -t $f2 -T "monks2.te.mff";
echo "=============================";

f3="monks3.tr.mff";
echo $f3;
echo "-----------------------------"
echo "# Naive Bayes #";
java NaiveBayes -t $f3 -T "monks3.te.mff";
echo "# iBk #";
java IBk -t $f3 -T "monks3.te.mff";
echo "=============================";
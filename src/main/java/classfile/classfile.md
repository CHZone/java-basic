Big-Endian（减少数据间隔）
无符号数

u4:java 标记：0XCAFEBABE（咖啡小子）
u2:次版本（是否也跟Big-Endian有关）
u2:主版本（45开始，1.0~1.1对应45，以后每个大版本依次加一1.7对应51）
u2:常量池中数据项数（编号从1开始，#1~#n，不包含n，#0在后面表示不引用任何数据项）


## 操作
linux 十六进制查看.class : od -Ax -tx1  filename.class


magic（0XCAFEBABE），`minor_version`,`major_version`

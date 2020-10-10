package com.aperture.community.core.common.status;

public enum  ImageType {

    /**
     * PNG是常见的一种图片格式，它最重要的特点是支持 alpha 通道透明度，也就是说，PNG图片支持透明背景。比如在使用Photoshop制作透明背景的圆形logo时，如果使用JPG格式，则图片背景会默认地存为白色，使用PNG格式则可以存为透明背景图片。
     * PNG格式图片也支持有损耗压缩，虽然PNG 提供的压缩量比JPG少，但PNG图片却比JPEG图片有更小的文档尺寸，因此现在越来越多的网络图像开始采用PNG格式。
     * */
    PNG("png"),
    /**
     * JPEG格式的压缩技术十分先进，能够将图像压缩在很小的储存空间，不过这种压缩是有损耗的，过度压缩会降低图片的质量。JPEG格式压缩的主要是高频信息，对色彩的信息保留较好，因此特别适合应用于互联网，可减少图像的传输和加载时间。
     * */
    JPG("jgp"),
    /**
     * TIFF格式，也叫做或TIF格式，可以支持不同颜色模式、路径、透明度、以及通道，是打印文档中最常用的格式
     * */
    TIFF("tiff"),
    /**
     * GIF也是一种压缩的图片格式，分为动态GIF和静态GIF两种。
     * GIF格式的最大特点是支持动态图片，并且支持透明背景。
     * */
    GIF("gif"),
    /**
     * BMP 格式是Windows操作系统中的标准图像文件格式，能够被多种Windows应用程序所支持。BMP格式包含的图像信息较丰富，几乎不进行压缩，但由此导致了它占用的存储空间很大，所以，目前BMP在单机上比较流行。
     * */
    BMP("bmp");

    private String value;

    ImageType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

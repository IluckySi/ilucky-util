package com.ilucky.util.image.property;

public class MainTest {

	public static void main(String[] args) {
		try {
			int[] size = ImageSizeUtil.getImageSizeFromUrl("http://image.taobao.com/bao/uploaded/i2/TB1ijnVOpXXXXbnXFXXYXGcGpXX_M2.SS2");
			System.out.println(size[0]);
			System.out.println(size[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

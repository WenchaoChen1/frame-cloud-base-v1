//// ====================================================
////
//// This file is part of the Riching Cloud Platform.
////
//// Create by Riching Tech <support@richingtech.com>
//// Copyright (c) 2020-2025 richingtech.com
////
//// ====================================================
//
//package com.gstdev.cloud.commons.utils;
//
//import cn.hutool.core.img.gif.GifDecoder;
//import com.gstdev.cloud.commons.image.gif.GifEncoder;
//import org.apache.commons.lang3.tuple.ImmutablePair;
//
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.util.List;
//
//public class GifUtils {
//
//  public static int loadGif(String gif, List<BufferedImage> list) throws IOException {
//    return loadGif(FileUtils.getInputStream(gif), list);
//  }
//
//  public static int loadGif(InputStream stream, List<BufferedImage> list) {
//    GifDecoder decoder = new GifDecoder();
//    decoder.read(stream);
//
//    int delay = 100;
//    for (int i = 0; i < decoder.getFrameCount(); i++) {
//      if (delay > decoder.getDelay(i)) {
//        delay = decoder.getDelay(i);
//      }
//      list.add(decoder.getFrame(i));
//    }
//
//    return delay;
//  }
//
//  public static void saveGif(List<BufferedImage> frames, int delay, String out) throws FileNotFoundException {
//    FileUtils.mkdir((new File(out)).getParentFile());
//    OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(out));
//
//    saveGif(frames, delay, outputStream);
//  }
//
//  public static void saveGif(List<BufferedImage> frames, int delay, OutputStream out) {
//    GifEncoder encoder = new GifEncoder();
//    encoder.setRepeat(0);
//    encoder.start(out);
//
//    encoder.setDelay(delay);
//    for (BufferedImage img : frames) {
//      encoder.setDelay(delay);
//      encoder.addFrame(img);
//    }
//    encoder.addFrame(frames.get(frames.size() - 1));
//    encoder.finish();
//  }
//
//  public static void saveGif(List<ImmutablePair<BufferedImage, Integer>> frames, OutputStream out) {
//    GifEncoder encoder = new GifEncoder();
//    encoder.setRepeat(0);
//    encoder.start(out);
//
//    for (ImmutablePair<BufferedImage, Integer> frame : frames) {
//      encoder.setDelay(frame.getRight());
//      encoder.addFrame(frame.getLeft());
//    }
//    encoder.finish();
//  }
//}

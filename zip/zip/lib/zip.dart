import 'dart:io';

import 'package:archive/archive.dart';
import 'package:archive/archive_io.dart';

void createZip() {
  final logDir = Directory.current.path + '/log';
  final zipLocation = Directory.current.path + '/log.zip';

  var encoder = ZipFileEncoder();
  encoder.zipDirectory(Directory(logDir), filename: zipLocation);

  // Manually create a zip of a directory and individual files.
  encoder.create(Directory.current.path + '/log2.zip');
  encoder.addFile(File('another_file.txt'));
  
  encoder.close();
}
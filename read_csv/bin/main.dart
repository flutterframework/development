import 'dart:io';
import 'dart:async';
import 'dart:convert';

main(List<String> arguments) {
  final File file = new File("/Users/sminrana/Downloads/C_stock_data.csv");

  Stream<List<int>> inputStream = file.openRead();

  inputStream
      .transform(utf8.decoder)       // Decode bytes to UTF-8.
      .transform(new LineSplitter()) // Convert stream to individual lines.
      .listen((String line) {        // Process results.

       List<String> row = line.split(','); // split by comma

        String id = row[0];
        String symbol = row[1];
        String open = row[2];
        String low = row[3];
        String high = row[4];
        String close = row[5];
        String exchange = row[6];
        String volume = row[7];
        String date = row[8];
        String timestamp = row[9];

        print('$id, $symbol, $open');

      },
      onDone: () { print('File is now closed.'); },
      onError: (e) { print(e.toString()); });
}

class GzipExample{
    public static String compress(byte[] content){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(content);
            gzipOutputStream.close();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        System.out.printf("Compression ratio %f\n", (1.0f * content.length/byteArrayOutputStream.size()));

        return new String(Base64.encodeBase64(byteArrayOutputStream.toByteArray()));
    }

    public static String decompress(String str){
        byte[] contentBytes = str.getBytes();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            IOUtils.copy(new GZIPInputStream(new ByteArrayInputStream(Base64.decodeBase64(contentBytes))), out);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        return new String(out.toByteArray());
    }
}

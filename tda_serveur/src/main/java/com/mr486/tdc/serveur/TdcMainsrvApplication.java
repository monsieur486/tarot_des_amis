package com.mr486.tdc.serveur;

import com.mr486.tdc.serveur.model.ChatServer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Objects;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.xml.bind.DatatypeConverter;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;

@SpringBootApplication
@Log4j2
public class TdcMainsrvApplication {

  public static final ChatServer chatServer = new ChatServer(8887);
  private static Long reunionActiveId;

  //@Value("${spring.profiles.active}")
  static
  String profil = "prod";

  public static void main(String[] args) {

    SpringApplication.run(TdcMainsrvApplication.class, args);

    log.warn("Initialisation du serveur websocket");

    if (Objects.equals(profil, "prod")){
      log.warn("WSS pris en compte");
      SSLContext context = getContext();
      if (context != null) {
        chatServer.setWebSocketFactory(new DefaultSSLWebSocketServerFactory(getContext()));
      }
      chatServer.setConnectionLostTimeout(30);
    } else {
      log.warn("WS pris en compte");
    }

    chatServer.start();

  }

  private static SSLContext getContext() {
    SSLContext context;
    String password = "";
    String pathname = "certs";
    try {
      context = SSLContext.getInstance("TLS");

      byte[] certBytes = parseDERFromPEM(getBytes(new File(pathname + File.separator + "cert.pem")),
        "-----BEGIN CERTIFICATE-----", "-----END CERTIFICATE-----");
      byte[] keyBytes = parseDERFromPEM(
        getBytes(new File(pathname + File.separator + "privkey.pem")),
        "-----BEGIN PRIVATE KEY-----", "-----END PRIVATE KEY-----");

      X509Certificate cert = generateCertificateFromDER(certBytes);
      RSAPrivateKey key = generatePrivateKeyFromDER(keyBytes);

      KeyStore keystore = KeyStore.getInstance("JKS");
      keystore.load(null);
      keystore.setCertificateEntry("cert-alias", cert);
      keystore.setKeyEntry("key-alias", key, password.toCharArray(), new Certificate[]{cert});

      KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
      kmf.init(keystore, password.toCharArray());

      KeyManager[] km = kmf.getKeyManagers();

      context.init(km, null, null);
    } catch (Exception e) {
      context = null;
    }
    return context;
  }

  private static byte[] parseDERFromPEM(byte[] pem, String beginDelimiter, String endDelimiter) {
    String data = new String(pem);
    String[] tokens = data.split(beginDelimiter);
    tokens = tokens[1].split(endDelimiter);
    return DatatypeConverter.parseBase64Binary(tokens[0]);
  }

  private static RSAPrivateKey generatePrivateKeyFromDER(byte[] keyBytes)
    throws InvalidKeySpecException, NoSuchAlgorithmException {
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);

    KeyFactory factory = KeyFactory.getInstance("RSA");

    return (RSAPrivateKey) factory.generatePrivate(spec);
  }

  private static X509Certificate generateCertificateFromDER(byte[] certBytes)
    throws CertificateException {
    CertificateFactory factory = CertificateFactory.getInstance("X.509");

    return (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(certBytes));
  }

  private static byte[] getBytes(File file) {
    byte[] bytesArray = new byte[(int) file.length()];

    FileInputStream fis = null;
    try {
      fis = new FileInputStream(file);
      fis.read(bytesArray); //read file into bytes[]
      fis.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return bytesArray;
  }

  public static Long idPartieActive(){
    return reunionActiveId;
  }

}

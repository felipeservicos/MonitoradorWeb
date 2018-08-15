/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wget;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Felipe Lopes
 */
public class Wget {

    /**
     * @param args the command line arguments
     */
    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) {
        System.setProperty("http.proxyHost", "YOUR-PROXY");
        System.setProperty("http.proxyPort", "YOUR-PROXY-PORT");
        while (true) {
            Long tempo = System.currentTimeMillis();
            String url = "http://YOUR-TARGET-SITE";


            URL u;

            ArrayList<String> parametro_de_Busca = new ArrayList<>();
            List<PalavrasChave> capturaLinha = new ArrayList<PalavrasChave>();
            String s = "";
            String x = "";

            parametro_de_Busca.add("SEARCH PARAMETERS IN TARGET SITE");
            Boolean resultado = true;
            try {

                for (String item : parametro_de_Busca) {

                    u = new URL(url);
                    InputStream is = u.openStream();
                    String aux = "";

                    BufferedReader d = new BufferedReader(new InputStreamReader(is));

                    do {

                        if (s != null) {

                            if (s.contains(item)) {
                                resultado = false;
                            } else {

                            }

                        }
                        aux = s;
                    } while ((s = d.readLine()) != null);
                    d.close();

                }
                if (resultado) {
                    System.out.println("Página Alterada");
                    System.out.println("Enviando SMS");
                    Wget http = new Wget();
                    http.sendGet();

                    System.exit(0);
                } else {

                }

            } catch (MalformedURLException mue) {
                System.err.println("Problema - URL errada");
                System.exit(2);
            } catch (IOException ioe) {
                System.err.println("Problema- Problema de IO. SEM INTERNET?");
                System.out.println(ioe.getCause());
                System.out.println(ioe);
                System.exit(3);
            } finally {

            }
            while ((System.currentTimeMillis() - tempo) < 300000) ;

        }
    }

    private static String RemoveEspecial(String x) {
        return x.replace("\"", "").replace(",", "").replace(")", "").replace(";", "").trim();

    }

    private void sendGet() throws MalformedURLException, IOException {
        System.clearProperty("http.proxyHost");
        System.clearProperty("http.proxyPort");

        String url = "http://yourAPI_for_SendSMS";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
//To change body of generated methods, choose Tools | Templates.
    }

    private void sendPost() throws MalformedURLException, IOException {
        String url = "https://selfsolve.apple.com/wcResults.do";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345"; // Fill here case need url params

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
}

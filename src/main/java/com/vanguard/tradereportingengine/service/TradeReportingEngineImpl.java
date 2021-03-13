package com.vanguard.tradereportingengine.service;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

@Service
public class TradeReportingEngineImpl implements TradeReportingEngine {

    private final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    private final XPathFactory xpathFactory = XPathFactory.newInstance();
    private final ClassLoader classLoader = getClass().getClassLoader();

    private final String INPUT_DIR = "input";

    private final String PAYMENT_AMOUNT = "//paymentAmount/amount";
    private final String CURRENCY = "//paymentAmount/currency";
    private final String SELLER_PARTY_REF = "//sellerPartyReference/@href";
    private final String BUYER_PARTY_REF = "//buyerPartyReference/@href";

    @Override
    public void processInputFiles() throws Exception {

        System.out.println("buyer_party,seller_party,premium_amount,premium_currency");
        try {
            int i = 0;
            do {
                String output = readFileAndFilter(INPUT_DIR + "/event" + i + ".xml");
                if (output != null)
                    System.out.println(output);
                i++;
            } while (true);
        } catch (FileNotFoundException fnfe) {
            // ignore the exception as this indicates we have read all the files
        } catch (Exception e) {
            throw e;
        }
    }

    public String readFileAndFilter(String filename) throws Exception {
        String output = null;
        DocumentBuilder builder;
        Document doc = null;
        try (InputStream stream = classLoader.getResourceAsStream(filename)) {
            if (stream == null) {
                throw new FileNotFoundException();
            }
            builder = docFactory.newDocumentBuilder();
            doc = builder.parse(stream);

            XPath xpath = xpathFactory.newXPath();
            XPathExpression expr = xpath.compile(PAYMENT_AMOUNT);
            String amount = (String) expr.evaluate(doc, XPathConstants.STRING);
            expr = xpath.compile(CURRENCY);
            String currency = (String) expr.evaluate(doc, XPathConstants.STRING);
            expr = xpath.compile(SELLER_PARTY_REF);
            String sellerParty = (String) expr.evaluate(doc, XPathConstants.STRING);
            expr = xpath.compile(BUYER_PARTY_REF);
            String buyerParty = (String) expr.evaluate(doc, XPathConstants.STRING);

            if (("EMU_BANK".equals(sellerParty) && "AUD".equals(currency))
                    || ("BISON_BANK".equals(sellerParty) && "USD".equals(currency))
                            && !isAnagram(buyerParty, sellerParty)) {
                output = buyerParty + "," + sellerParty + "," + amount + "," + currency;
            }
        } catch (SAXParseException spe) {
            // ignore the Parse exception to continue peocessing other files
        } catch (Exception e) {
            // e.printStackTrace();
            throw e;
        }
        return output;
    }

    // Check for seller and buyer Anagram
    private boolean isAnagram(String input1, String input2) {
        if (input1.length() != input2.length()) {
            return false;
        }
        Multiset<Character> multiset1 = HashMultiset.create();
        Multiset<Character> multiset2 = HashMultiset.create();
        for (int i = 0; i < input1.length(); i++) {
            multiset1.add(input1.charAt(i));
            multiset2.add(input2.charAt(i));
        }
        return multiset1.equals(multiset2);
    }

}

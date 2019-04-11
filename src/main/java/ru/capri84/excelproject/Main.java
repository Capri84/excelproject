package ru.capri84.excelproject;

import ru.capri84.excelproject.creators.CreatePDF;
import ru.capri84.excelproject.creators.CreateXLSX;
import ru.capri84.excelproject.network.RequestUtils;

class Main {

    public static void main(String[] args) {
        CreateXLSX.createXLSX();
        CreateXLSX.createXlsxHeader();
        CreatePDF.createPdf(CreatePDF.DEST_PDF);
        CreatePDF.createPdfHeader();
        RequestUtils.makeRequest();
    }
}

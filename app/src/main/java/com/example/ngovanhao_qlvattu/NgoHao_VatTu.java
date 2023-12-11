package com.example.ngovanhao_qlvattu;

public class NgoHao_VatTu {

  private String maVatTu;

  private String tenVatTu;

  private String nhaSanXuat;

  private String namSanXuat;

  private String soLuong;

  private String donGia;

  public NgoHao_VatTu() {

  }

  public NgoHao_VatTu(String maVatTu, String tenVatTu, String nhaSanXuat, String namSanXuat, String soLuong, String donGia) {
    this.maVatTu = maVatTu;
    this.tenVatTu = tenVatTu;
    this.nhaSanXuat = nhaSanXuat;
    this.namSanXuat = namSanXuat;
    this.soLuong = soLuong;
    this.donGia = donGia;
  }

  public String getMaVatTu() {
    return maVatTu;
  }

  public void setMaVatTu(String maVatTu) {
    this.maVatTu = maVatTu;
  }

  public String getTenVatTu() {
    return tenVatTu;
  }

  public void setTenVatTu(String tenVatTu) {
    this.tenVatTu = tenVatTu;
  }

  public String getNhaSanXuat() {
    return nhaSanXuat;
  }

  public void setNhaSanXuat(String nhaSanXuat) {
    this.nhaSanXuat = nhaSanXuat;
  }

  public String getNamSanXuat() {
    return namSanXuat;
  }

  public void setNamSanXuat(String namSanXuat) {
    this.namSanXuat = namSanXuat;
  }

  public String getSoLuong() {
    return soLuong;
  }

  public void setSoLuong(String soLuong) {
    this.soLuong = soLuong;
  }

  public String getDonGia() {
    return donGia;
  }

  public void setDonGia(String donGia) {
    this.donGia = donGia;
  }

  @Override
  public String toString() {
    return "NgoHao_VatTu{" +
        "maVatTu='" + maVatTu + '\'' +
        ", tenVatTu='" + tenVatTu + '\'' +
        ", nhaSanXuat='" + nhaSanXuat + '\'' +
        ", namSanXuat=" + namSanXuat +
        ", soLuong=" + soLuong +
        ", donGia=" + donGia +
        '}';
  }

}

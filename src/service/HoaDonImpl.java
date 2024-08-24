/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.HoaDon;
import model.HoaDonViewModel;

/**
 *
 * @author admin
 */
public interface HoaDonImpl {
    Integer saveHD(HoaDon hoaDon, int idNV);
    List<HoaDonViewModel> getListHD(int TrangThai);
}

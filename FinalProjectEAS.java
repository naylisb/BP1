package finalkasir;
import java.util.Scanner;
public class FinalProjectEAS {
    static String[] daftarBarang = {"Nasi Goreng Mawut", "Nasi Goreng Jawa", "Nasi Goreng Sosis", "Cap Cay Goreng", "Cap Cay Merah", "Tamie Cap Cay", "Mie Goreng", "Mie Goreng Sosis", "Mie Kuah"};
    static double[] hargaBarang = {10000, 12000, 15000, 16000, 17000, 19000, 10000, 13000, 12000};
    static int[] jumlahTerjual = new int[daftarBarang.length];
    static double totalSemuaTransaksi = 0;
    
    public static int mCari(String[] data, String kata){
        for (int i = 0; i < data.length; i++) {
            if(data[i].equalsIgnoreCase(kata)) {
                return i;
            }
        }
        return -1;
    }
    public static void sortBarangBySales(String[] barang, int[] terjual) {
        for (int i = 0; i < terjual.length-1; i++) {
            for (int j = 0; j < terjual.length-i-1; j++) {
                if(terjual[j]<terjual[j+1]) {
                    int temp = terjual[j];
                    terjual[j] = terjual[j+1];
                    terjual[j+1] = temp;
                    
                    String temp2 = barang[j];
                    barang[j] = barang[j+1];
                    barang[j+1] = temp2;
                }
            }
        }
    }
    public static void tampilkanBarang() { 
        System.out.println("\nDaftar Barang dan Harga : ");
        for (int i = 0; i < daftarBarang.length; i++) {
            System.out.println((i+1)+". "+daftarBarang[i]+" : Rp."+hargaBarang[i]);            
        }
    }
    public static void tambahBarang(Scanner sc) {
        System.out.print("Nama barang baru : ");
        String namaBaru = sc.nextLine();
        System.out.print("Harga barang baru : Rp.");
        double hargaBaru = sc.nextDouble();
        
        String[] tempDaftarBarang = new String[daftarBarang.length+1];
        double[] tempHargaBarang = new double[hargaBarang.length+1];
        int[] tempJumlahTerjual = new int[jumlahTerjual.length+1];
        
        for (int i = 0; i < daftarBarang.length; i++) {
            tempDaftarBarang[i] = daftarBarang[i];
            tempHargaBarang[i] = hargaBarang[i];
            tempJumlahTerjual[i] = jumlahTerjual[i];
        }
       
        tempDaftarBarang[daftarBarang.length] = namaBaru;
        tempHargaBarang[hargaBarang.length] = hargaBaru;
        tempJumlahTerjual[jumlahTerjual.length] = 0;
        
        daftarBarang = tempDaftarBarang;
        hargaBarang = tempHargaBarang;
        jumlahTerjual = tempJumlahTerjual;
        
        System.out.println("Barang berhasil ditambahkan.");        
    }
    public static void ubahHargaBarang(Scanner sc) {
        System.out.print("Nama barang yang ingin diubah : ");
        String namaBarang = sc.nextLine();
        int index = mCari(daftarBarang, namaBarang);
        
        if(index!=-1) {
            System.out.print("Harga baru : ");
            hargaBarang[index] = sc.nextDouble();
            System.out.println("Harga berhasil diubah.");
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }
    public static void hapusBarang(Scanner sc) {
        System.out.print("Nama barang yang ingin dihapus : ");
        String namaBarang = sc.nextLine();
        int index = mCari(daftarBarang, namaBarang);
        
        if(index!=-1) {
            String[] tempDaftarBarang = new String[daftarBarang.length-1];
            double[] tempHargaBarang = new double[hargaBarang.length-1];
            int[] tempJumlahTerjual = new int[jumlahTerjual.length-1];
            
            for (int i = 0, j = 0; i < daftarBarang.length; i++) {
                if(i!=index) {
                    tempDaftarBarang[j] = daftarBarang[i];
                    tempHargaBarang[j] = hargaBarang[i];
                    tempJumlahTerjual[j] = jumlahTerjual[i];
                    j++;
                }
            }
            daftarBarang = tempDaftarBarang;
            hargaBarang = tempHargaBarang;
            jumlahTerjual = tempJumlahTerjual;
            System.out.println("Barang berhasil dihapus.");
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }
    public static void kasir(Scanner sc) {
        double totalBiaya = 0;
        int pilihBarang;
        do {
            System.out.println("\n-------- Menu Kasir --------");
            for (int i = 0; i < daftarBarang.length; i++) {
                System.out.println((i+1)+". "+daftarBarang[i]+" : Rp."+hargaBarang[i]+"/porsi");                
            }
            System.out.println((daftarBarang.length+1)+". Selesai");
            System.out.print("Pilih barang : ");
            pilihBarang = sc.nextInt();
            
            if(pilihBarang==daftarBarang.length+1) break;
            
            if(pilihBarang>0&&pilihBarang<=daftarBarang.length) {
                System.out.print("Jumlah porsi : ");
                int jumlahPorsi = sc.nextInt();
                int index = pilihBarang-1;
                totalBiaya += hargaBarang[index]*jumlahPorsi;
                jumlahTerjual[index] += jumlahPorsi;
            } else {
                System.out.println("Pilihan tidak valid.");
            }               
        } while(true);
        System.out.println("\nTotal Biaya : Rp."+totalBiaya);
        totalSemuaTransaksi += totalBiaya;
    }
    public static void admin(Scanner sc) {
        int pilih;
        do {
            System.out.println("\n========= Admin =========");
            System.out.println("1. Tampilkan barang\n2. Tambah barang\n3. Ubah harga barang\n4. Hapus barang\n5. Kembali ke menu utama");
            System.out.print("Pilih opsi : ");
            pilih = sc.nextInt();
            sc.nextLine();
            
            switch(pilih) {
                case 1:
                    tampilkanBarang();
                    break;
                case 2:
                    tambahBarang(sc);
                    break;
                case 3:
                    ubahHargaBarang(sc);
                    break;
                case 4:
                    hapusBarang(sc);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while(true);
    }
    public static void owner() {
        System.out.println("\n======== Owner ========");
        System.out.println("Total semua transaksi : Rp."+totalSemuaTransaksi);
        String[] barangCopy = new String[daftarBarang.length];
        int[] terjualCopy = new int[jumlahTerjual.length];
        for (int i = 0; i < daftarBarang.length; i++) {
            barangCopy[i] = daftarBarang[i];
            terjualCopy[i] = jumlahTerjual[i];
        }
        sortBarangBySales(barangCopy, terjualCopy);
        System.out.println("\n5. Barang Paling Laris : ");
        for (int i = 0; i < 5 && i < barangCopy.length; i++) {
            System.out.println((i+1)+". "+barangCopy[i]+" : "+terjualCopy[i]+" terjual");            
        }
    }    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pilihMenu;
        do {
            System.out.println("\n===== Warung Cak Yono =====");
            System.out.println("1. Kasir\n2. Admin\n3. Owner\n4. Exit");
            System.out.print("Pilih menu utama : ");
            pilihMenu = sc.nextInt();
            
            switch(pilihMenu) {
                case 1:
                    kasir(sc);
                    break;
                case 2:
                    admin(sc);
                    break;
                case 3:
                    owner();
                    break;
                case 4:
                    System.out.println("Keluar dari program. Terima kasih :)");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while(pilihMenu!=4);
        sc.close();
    }
}
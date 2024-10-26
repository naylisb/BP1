package finalkasir;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FinalKasir {
    static double nasiGorengMawut = 10000;
    static double nasiGorengJawa = 12000;
    static double nasiGorengSosis = 15000;
    static double capCayGoreng = 16000;
    static double capCayMerah = 17000;
    static double capCayTamie = 19000;
    static double mieGoreng = 10000;
    static double mieGorengSosis = 13000;
    static double mieKuah = 12000;
    static double totalSemuaTransaksi = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pilihMenu;
        do {
            System.out.println("\n===== Warung Cak Yono =====");
            System.out.println("1. Kasir\n2. Admin\n3. Owner\n4. Exit");
            System.out.print("Pilih menu utama : ");
            pilihMenu = sc.nextInt();
            
            switch (pilihMenu) {
                case 1:
                    kasir(sc); // Panggil fungsi kasir
                    break;
                case 2:
                    admin(sc); // Panggil fungsi admin
                    break;
                case 3:
                    owner(); // Panggil fungsi owner
                    break;
                case 4:
                    System.out.println("Keluar dari program. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");}
        } while (pilihMenu != 4);
        sc.close();}        
    
    public static void kasir(Scanner sc) {
        double totalBiaya=0, grandTotal=0, diskon, totalAkhir, uangDibayar, kembalian;
        int pilihMenu;
        
        do {
            System.out.println("\n-------- Menu kasir --------");
            System.out.println("1. Nasi Goreng\n2. Cap Cay\n3. Mie\n4. Selesai");
            System.out.print("Pilih menu : ");
            pilihMenu = sc.nextInt();
            
            if (pilihMenu == 4) break;
            
            switch (pilihMenu) {
                case 1:
                    System.out.println("------ Nasi Goreng ------");
                    System.out.println("1. Nasi Goreng Mawut  : Rp."+nasiGorengMawut+"/porsi");
                    System.out.println("2. Nasi Goreng Jawa   : Rp."+nasiGorengJawa+"/porsi");
                    System.out.println("3. Nasi Goreng Sosis  : Rp."+nasiGorengSosis+"/porsi");
                    System.out.print("Pilih nasi goreng     : ");
                    int nasiGoreng = sc.nextInt();
                    System.out.print("Jumlah porsi          : ");
                    int porsiNasGor = sc.nextInt();
                    
                    if (nasiGoreng == 1) {
                        totalBiaya = porsiNasGor*nasiGorengMawut;
                    } else if (nasiGoreng == 2) {
                        totalBiaya = porsiNasGor*nasiGorengJawa;
                    } else if (nasiGoreng == 3) {
                        totalBiaya = porsiNasGor*nasiGorengSosis;
                    } else {
                        System.out.println("Pilihan tidak ada.");
                        continue;}
                    break;
                case 2:
                    System.out.println("------- Cap Cay -------");
                    System.out.println("1. Cap Cay Goreng   : Rp."+capCayGoreng+"/porsi");
                    System.out.println("2. Cap Cay Merah    : Rp."+capCayMerah+"/porsi");
                    System.out.println("3. Tamie Cap Cay    : Rp."+capCayTamie+"/porsi");
                    System.out.print("Pilih cap cay       : ");
                    int capCay = sc.nextInt();
                    System.out.print("Jumlah porsi        : ");
                    int porsiCapCay = sc.nextInt();
                    
                    if (capCay == 1) {
                        totalBiaya = porsiCapCay*capCayGoreng;                     
                    } else if (capCay == 2) {
                        totalBiaya = porsiCapCay*capCayMerah;
                    } else if (capCay == 3) {
                        totalBiaya = porsiCapCay*capCayTamie;
                    } else {
                        System.out.println("Pilihan tidak ada.");}
                    break;
                case 3:
                    System.out.println("-------- Mie --------");
                    System.out.println("1. Mie Goreng        : Rp."+mieGoreng+"/porsi");
                    System.out.println("2. Mie Goreng Sosis  : Rp."+mieGorengSosis+"/porsi");
                    System.out.println("3. Mie Kuah          : Rp."+mieKuah+"/porsi");
                    System.out.print("Pilih mie            : ");
                    int Mie = sc.nextInt();
                    System.out.print("Jumlah porsi         : ");
                    int porsiMie = sc.nextInt();
                    
                    if (Mie == 1) {
                        totalBiaya = porsiMie*mieGoreng;
                    } else if (Mie == 2) {
                        totalBiaya = porsiMie*mieGorengSosis;
                    } else if (Mie == 3) {
                        totalBiaya = porsiMie*mieKuah;
                    } else {
                        System.out.println("pilihan tidak ada.");}
                    break;
                default:
                    System.out.println("Pilihan menu tidak valid.");}
            grandTotal += totalBiaya;
            System.out.println("Total Biaya           : Rp."+totalBiaya);
            System.out.println("Grand Total           : Rp."+grandTotal);
        } while (pilihMenu != 4);
        
        System.out.println("\n======== Pembayaran ========");
        System.out.println("Grand Total           : Rp."+grandTotal);
        
        if (grandTotal >= 150000) {
            System.out.println("Anda mendapat diskon 5%");
            diskon = grandTotal*0.05;
        } else {
            diskon = 0;
            System.out.println("Anda tidak mendapat diskon.");}
        
        totalAkhir = grandTotal - diskon;
        System.out.println("Total Akhir           : Rp."+totalAkhir);
        
        System.out.print("Uang yang dibayar     : Rp.");
        uangDibayar = sc.nextDouble();
        
        if (uangDibayar > totalAkhir) {
            kembalian = uangDibayar-totalAkhir;
            System.out.print("Kembalian             : Rp."+kembalian);
        } else if (uangDibayar == totalAkhir) {
            System.out.println("Uang pas.");
            kembalian = 0;
        } else {
            System.out.println("Uang yang dibayarkan kurang.");
            return;}
        
        totalSemuaTransaksi += totalAkhir;
        simpanTransaksiKeCSV(grandTotal, totalAkhir, diskon, uangDibayar, kembalian);}
    
    public static void admin(Scanner sc) {
        int pilih;
        System.out.println("\n========= Admin =========");
        System.out.println("1. Ubah harga Nasi Goreng\n2. Ubah harga Cap Cay\n3. Ubah harga Mie");
        System.out.print("Pilih opsi : ");
        pilih = sc.nextInt();
        
        switch (pilih) {
            case 1:
                System.out.print("Harga baru Nasi Goreng Mawut : Rp.");
                nasiGorengMawut = sc.nextDouble();
                System.out.print("Harga baru Nasi Goreng Jawa  : Rp.");
                nasiGorengJawa = sc.nextDouble();
                System.out.print("Harga baru Nasi Goreng Sosis : Rp.");
                nasiGorengSosis = sc.nextDouble();
                break;
            case 2:
                System.out.print("Harga baru Cap Cay Goreng    : Rp.");
                capCayGoreng = sc.nextDouble();
                System.out.print("Harga baru Cap Cay Merah     : Rp.");
                capCayMerah = sc.nextDouble();
                System.out.print("Harga baru Tamie Cap Cay     : Rp.");
                capCayTamie = sc.nextDouble();
                break;
            case 3:
                System.out.print("Harga baru Mie Goreng        : Rp.");
                mieGoreng = sc.nextDouble();
                System.out.print("Harga baru Mie Goreng Sosis  : Rp.");
                mieGorengSosis = sc.nextDouble();
                System.out.print("Harga baru Mie Kuah          : Rp.");
                mieKuah = sc.nextDouble();
                break;
            default:
                System.out.println("Pilihan tidak valid.");}}
    
    public static void owner() {
        System.out.println("\n======== Owner ========");
        System.out.println("Total semua transaksi penjualan : Rp."+totalSemuaTransaksi);}
    
     public static void simpanTransaksiKeCSV(double grandTotal, double totalAkhir, double diskon, double uangDibayar, double kembalian) {
        try {
            File file = new File("data_transaksi_warung.csv");
            FileWriter writer;
            
            // Jika file tidak ada, buat file baru dan tulis header
            if (!file.exists()) {
                writer = new FileWriter(file);
                writer.write("Grand Total\tTotal Akhir\tDiskon\tUang Dibayar\tKembalian\n");
            } else {
                writer = new FileWriter(file, true);}
            
            // Tulis transaksi ke file
            writer.write(grandTotal + "\t" + totalAkhir + "\t" + diskon + "\t" + uangDibayar + "\t" + kembalian + "\n");
            writer.close();
            System.out.println("\nTransaksi berhasil disimpan di data_transaksi_warung.csv");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan transaksi : "+e.getMessage());
        }}}

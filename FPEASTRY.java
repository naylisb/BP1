package finalkasir;
import java.util.Scanner;
public class FPEASTRY {
    static String[] mainMenu = {"Nasi Goreng", "Cap Cay", "Mie"};
    static String[][] subMenu = {
        {"NasGor Jawa", "NasGor Sosis", "NasGor Seafood"},
        {"Cap Cay Goreng", "Cap Cay Merah", "Tamie Cap Cay"},
        {"Mie Goreng", "Mie Sosis", "Mie Seafood"}
    };
    static int[][] subMenuHarga = {
        {12000, 15000, 19000},
        {16000, 17000, 19000},
        {10000, 13000, 12000}
    };

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
                    owner(sc); // Panggil fungsi owner
                    break;
                case 4:
                    System.out.println("Keluar dari program. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihMenu!=4);
        sc.close();
    }
    
    static int mCari(String data[], String kata) {
        for (int i = 0; i < data.length; i++) {
            if (data[i]!=null && data[i].equalsIgnoreCase(kata)) {
                return i;
            }
        }
        return -1;
    }    
    
    static void tampilkanMenu() {
        System.out.println("\n======= Daftar Menu =======");
        for (int i = 0; i < mainMenu.length; i++) {
            System.out.println("- "+mainMenu[i]+" :");
            for (int j = 0; j < subMenu[i].length; j++) {
                if (subMenu[i][j]!=null) {
                    System.out.print("   "+(j+1)+" "+subMenu[i][j]);
                    System.out.println(" ["+subMenuHarga[i][j]+"]");
                }
            }
        }
    }
    
    public static void kasir(Scanner sc) {
    double grandTotal = 0, diskon, totalAkhir, uangDibayar, kembalian;
    int pilihSubMenu = 0, idx = 0;
    String[] menu = new String[100];
    int[] harga = new int[100];
    int[] porsi = new int[100];
    do {
        System.out.println("\n-------- Menu Kasir --------");            
        tampilkanMenu();
        System.out.println("0. Selesai");
        System.out.print("Pilih menu utama : ");
        sc.nextLine(); 
        String namaMainMenu = sc.nextLine();
        if (namaMainMenu.equals("0")) {
            break;
        }
        int mainMenuIdx = mCari(mainMenu, namaMainMenu);
        if (mainMenuIdx==-1) {
            System.out.println("Menu utama tidak ada. Coba lagi!");
            continue;
        }
    
        System.out.println("\nPilih sub menu : ");
        for (int i = 0; i < subMenu[mainMenuIdx].length; i++) {
            System.out.print((i+1)+". "+subMenu[mainMenuIdx][i]);                
            System.out.println(" ["+subMenuHarga[mainMenuIdx][i]+"]");
        }
        
        System.out.print("Pilih sub menu : ");
        pilihSubMenu = sc.nextInt();
        if (pilihSubMenu == 0) {
            break; 
        } else if (pilihSubMenu<1 || pilihSubMenu>subMenu[mainMenuIdx].length) {
            System.out.println("Pilihan tidak valid.");
            continue;
        }

        System.out.print("Jumlah porsi : ");
        int Porsi = sc.nextInt();
        menu[idx] = subMenu[mainMenuIdx][pilihSubMenu-1];
        harga[idx] = subMenuHarga[mainMenuIdx][pilihSubMenu-1];
        porsi[idx] = Porsi;
        
        double totalBiaya = subMenuHarga[mainMenuIdx][pilihSubMenu - 1]*Porsi;
        grandTotal+=totalBiaya;
        idx++;
    } while (true);  
    
    System.out.println("\n============ Daftar Pesanan ============");
    System.out.println("No.\tMenu\t\tHarga\tJumlah");
    for (int i = 0; i < idx; i++) {
        System.out.print((i+1)+".\t"+menu[i]);
        System.out.print("\t"+harga[i]);
        System.out.println("\t"+porsi[i]);        
    }
    System.out.println("----------------------------------------");
    diskon = 0;
    if (grandTotal >= 150000) {
        diskon = grandTotal*0.05;
        System.out.println("Selamat! Anda mendapat diskon 5%.");
    }

    totalAkhir = grandTotal-diskon;
    System.out.println("\nTotal akhir   : Rp."+totalAkhir);    
    System.out.print("Uang diterima : Rp.");
    uangDibayar = sc.nextInt();
    kembalian = uangDibayar-totalAkhir;
    
    if (kembalian < 0) {
        System.out.println("Uang Anda tidak cukup.");
    } else {
        System.out.println("Kembalian     : Rp."+kembalian);
    }
}             
    
    public static void admin(Scanner sc) {
        int pilihAdmin;
        do {
            System.out.println("\n-------- Menu Admin --------");
            System.out.println("1. Tampilkan Menu");
            System.out.println("2. Tambah atau Ubah Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali");
            System.out.print("Pilih : ");
            pilihAdmin = sc.nextInt();
            switch (pilihAdmin) {
                case 1:
                    tampilkanMenu();
                    break;
                case 2: // tambah menu
                    System.out.println("\n===== Tambah Menu =====");
                    System.out.print("Nama main menu baru : ");
                    sc.nextLine();
                    String namaMainMenu = sc.nextLine();
                    System.out.print("Jumlah maks. sub menu : ");
                    int jumlahSubMenu = sc.nextInt();
                    
                    String[] newMainMenu = new String[mainMenu.length+1];
                    String[][] newSubMenu = new String[subMenu.length+1][];
                    int[][] newSubMenuHarga = new int[subMenuHarga.length+1][];
                    
                    for (int i = 0; i < mainMenu.length; i++) {
                        newMainMenu[i] = mainMenu[i];
                        newSubMenu[i] = subMenu[i];
                        newSubMenuHarga[i] = subMenuHarga[i];
                    }
                    
                    newMainMenu[mainMenu.length] = namaMainMenu;
                    newSubMenu[subMenu.length] = new String[jumlahSubMenu];
                    newSubMenuHarga[subMenuHarga.length] = new int[jumlahSubMenu];
                    
                    sc.nextLine();
                    for (int i = 0; i < jumlahSubMenu; i++) {
                        System.out.print("Sub menu "+(i+1)+" : ");
                        newSubMenu[subMenu.length][i] = sc.nextLine();
                        System.out.print("Harga sub menu "+(i+1)+" : ");
                        newSubMenuHarga[subMenuHarga.length][i] = sc.nextInt();
                        sc.nextLine();                        
                    }
                    
                    mainMenu = newMainMenu;
                    subMenu = newSubMenu;
                    subMenuHarga = newSubMenuHarga;                    
                    System.out.println("Menu berhasil ditambahkan.");
                    break;
                case 3:    
                    System.out.println("\n===== Hapus Menu =====");
                    tampilkanMenu();
                    System.out.print("Pilih main menu : ");
                    int pilihMainMenu = sc.nextInt()-1;
                    
                    if (pilihMainMenu>=0 && pilihMainMenu < mainMenu.length) {
                        System.out.print("Sub menu yang akan dihapus : ");
                        sc.nextLine();
                        String namaSubMenu = sc.nextLine();
                        int subMenuIdx = mCari(subMenu[pilihMainMenu], namaSubMenu);
                        if (subMenuIdx!=-1) {
                            subMenu[pilihMainMenu][subMenuIdx] = null;
                            subMenuHarga[pilihMainMenu][subMenuIdx]=0;
                            System.out.println("Sub menu berhasil dihapus.");
                        } else {
                            System.out.println("Sub menu tidak ditemukan.");
                        }
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    } break;     
                case 4:
                    System.out.println("Kembali ke menu utama.");
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihAdmin!=4);
    }
    
    public static void owner(Scanner sc) {
        
    }    
}     
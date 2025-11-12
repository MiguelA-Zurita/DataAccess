package org.example;

import java.time.LocalDate;

public class Models {
    public static class Customer {
        public final String dni;
        public final String name;
        public final String firstSurname;
        public final String lastSurname;

        public Customer(String dni, String name, String firstSurname, String lastSurname) {
            this.dni = dni;
            this.name = name;
            this.firstSurname = firstSurname;
            this.lastSurname = lastSurname;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "dni='" + dni + '\'' +
                    ", name='" + name + '\'' +
                    ", firstSurname='" + firstSurname + '\'' +
                    ", lastSurname='" + lastSurname + '\'' +
                    '}';
        }
    }

    public static class Item {
        public final int id;
        public final double unitPrice;
        public final String name;
        public final int stock;

        public Item(int id, double unitPrice, String name, int stock) {
            this.id = id;
            this.unitPrice = unitPrice;
            this.name = name;
            this.stock = stock;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    ", unitPrice=" + unitPrice +
                    ", name='" + name + '\'' +
                    ", stock=" + stock +
                    '}';
        }
    }

    public static class Sale {
        public final int id;
        public final double totalPrice;
        public final LocalDate salesDate;
        public final String dniMember; // nullable

        public Sale(int id, double totalPrice, LocalDate salesDate, String dniMember) {
            this.id = id;
            this.totalPrice = totalPrice;
            this.salesDate = salesDate;
            this.dniMember = dniMember;
        }

        @Override
        public String toString() {
            return "Sale{" +
                    "id=" + id +
                    ", totalPrice=" + totalPrice +
                    ", salesDate=" + salesDate +
                    ", dniMember=" + (dniMember == null ? "null" : ('"' + dniMember + '"')) +
                    '}';
        }
    }

    public static class ItemInSale {
        public final int saleId;
        public final int itemId;
        public final String itemName;
        public final double unitPrice;
        public final int quantity;
        public final double subtotal;

        public ItemInSale(int saleId, int itemId, String itemName, double unitPrice, int quantity) {
            this.saleId = saleId;
            this.itemId = itemId;
            this.itemName = itemName;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
            this.subtotal = unitPrice * quantity;
        }

        @Override
        public String toString() {
            return "ItemInSale{" +
                    "saleId=" + saleId +
                    ", itemId=" + itemId +
                    ", itemName='" + itemName + '\'' +
                    ", unitPrice=" + unitPrice +
                    ", quantity=" + quantity +
                    ", subtotal=" + subtotal +
                    '}';
        }
    }
}

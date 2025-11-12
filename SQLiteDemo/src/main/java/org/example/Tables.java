package org.example;

import java.time.LocalDate;

public class Tables {
    public record Customer(String dni, String name, String firstSurname, String lastSurname) {
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

    public record Item(int id, double unitPrice, String name, int stock) {
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

    /**
     * @param dniMember nullable
     */
    public record Sale(int id, double totalPrice, LocalDate salesDate, String dniMember) {
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

package modelos;

import com.google.gson.annotations.SerializedName;

public class MoedasConvertidas {
    @SerializedName("target_code")
    private String nomeConvertido;

    @SerializedName("conversion_rate")
    private double valorConvertido;


    @Override
    public String toString() {
        return  "Moeda Convertida= " + nomeConvertido +
                ", valorConvertido= " + valorConvertido ;
    }
}

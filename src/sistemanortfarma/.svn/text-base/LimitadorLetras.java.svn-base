package sistemanortfarma;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Miguel Gomez S.
 */
public class LimitadorLetras extends PlainDocument {

    //METODO QUE AVECPTA SOLO ESCRIBIR NUMEROS
    private int v_numeroMaximoDigitos;
    private JTextField v_Editor;

    public LimitadorLetras(JTextField p_Editor, int p_numeroMaximoDigitos) {
        this.v_Editor = p_Editor;
        this.v_numeroMaximoDigitos = p_numeroMaximoDigitos;
    }

    public void insertString(int arg0, String arg1, AttributeSet arg2) throws BadLocationException {
        for (int i = 0; i < arg1.length(); i++) {
            if (!(Character.isDigit(arg1.charAt(i)))) {//si no es  un digito, salgo
                return;
            }

            if ((v_Editor.getText().length() + arg1.length()) > this.v_numeroMaximoDigitos) {//si llego al máximo de dígitos introducidos salgo
                return;
            }
        }
        //si llego aca esta todo bien, llamo a la superclase que muestra los dígitos en el editor
        super.insertString(arg0, arg1, (javax.swing.text.AttributeSet) arg2);
    }
}

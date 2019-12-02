/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemanortfarma;

import CapaDatos.*;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

/**
 *
 * @author gparedes
 */
public class Align1 {

    public static final int JUST_LEFT = 'l';
        public static final int JUST_CENTER = 'c';
        public static final int JUST_RIGHT = 'r';

        private String fill;
        private int just;
        private int maxChars;


        public Align1(int maxChars, int just, String filler) {

            switch(just) {

            case JUST_LEFT:

            case JUST_CENTER:

            case JUST_RIGHT:

                this.just = just;

                break;

            default:

                throw new IllegalArgumentException("Parametro de Alineacion invalida.");

            }

            if (maxChars < 0) {

                throw new IllegalArgumentException("Debes proporcionar valores positivos.");

            }

            if (filler==null || filler.length()==0)
                this.fill=" ";
            else
                this.fill=filler;

            this.maxChars = maxChars;

        }


        public StringBuffer format(Object obj, StringBuffer donde, FieldPosition ignorarEsto)  {

            String s = (String)obj;
            String wanted = s.substring(0, Math.min(s.length( ), maxChars));

             switch (just) {

                case JUST_RIGHT:

                    rellenar(donde, maxChars - wanted.length( ));

                    donde.append(wanted);

                    break;

                case JUST_CENTER:

                    int startPos = donde.length( );

                    rellenar(donde, (maxChars - wanted.length( ))/2);

                    donde.append(wanted);

                    rellenar(donde, (maxChars - wanted.length( ))/2);

                    // Ajuste por redondeo

                    rellenar(donde, maxChars - (donde.length( ) - startPos));

                    break;

                case JUST_LEFT:

                    donde.append(wanted);

                    rellenar(donde, maxChars - wanted.length( ));

                    break;

                }

            return donde;

        }

        /*
         * Metodo para rellenar con caracteres los espacios para alinear
         */
        protected final void rellenar(StringBuffer origen, int cuantos) {

            for (int i=0; i<cuantos; i++)

                origen.append(this.fill);

        }


        String format(String s) {

            return format(s, new StringBuffer( ),null).toString( );

        }



        public Object parseObject (String source, ParsePosition pos)  {

            return source;

        }

}

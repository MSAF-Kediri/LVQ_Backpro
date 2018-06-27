/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvq_backpro;

import java.awt.Cursor;
import java.awt.Frame;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * BISMILLAHIRRAHMANIRRAHIIM
 *
 * @author MSAF
 */
public class LoaderProcess extends SwingWorker<String, Void> {

    private JFrame frame;
    public long lamaWaktu;
    private Calendar time;
   

    public LoaderProcess(JFrame frame) {
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        this.frame = frame;
        time = Calendar.getInstance();
        lamaWaktu = 99;
        
    }

    @Override
    public String doInBackground() throws IOException {

        String result = null;
        // read file and set result;
        return result;
    }

    @Override
    public void done() {
        try {
            String result = get();

            //do stuff 
        } catch (ExecutionException | InterruptedException ex) {
            // display error
            JOptionPane.showMessageDialog(new Frame(),
                    ex.getMessage(),
                    "Error opening file",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            frame.setCursor(Cursor.getDefaultCursor());
            Calendar time2 = Calendar.getInstance();
            long temp = time2.getTimeInMillis() - time.getTimeInMillis();
            System.out.println(temp);
            lamaWaktu = Math.round(temp / 1000D);
            System.out.println(lamaWaktu + "detik");
           
        }
    }

}

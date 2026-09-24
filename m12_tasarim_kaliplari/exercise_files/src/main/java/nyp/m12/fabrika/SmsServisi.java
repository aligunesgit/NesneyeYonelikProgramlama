package nyp.m12.fabrika;

/** M12 - Factory Method: SMS bildirimi oluşturan somut servis. */
public class SmsServisi extends BildirimServisi {

    @Override
    protected Bildirim bildirimOlustur(String alici) {
        return new Bildirim.Sms(alici);
    }
}

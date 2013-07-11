package dk.kleistsvendsen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class TicSourceTest {
    @Test
    public void testTic() {
        TicSource ticSource = new TicSource();
        Long tic0 = ticSource.tic();
        assert(tic0 < ticSource.tic());
    }

}

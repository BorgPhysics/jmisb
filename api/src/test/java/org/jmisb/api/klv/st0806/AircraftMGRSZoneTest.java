package org.jmisb.api.klv.st0806;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.jmisb.api.common.KlvParseException;
import org.testng.annotations.Test;

public class AircraftMGRSZoneTest {
    @Test
    public void testConstructFromValue() {
        // Min
        AircraftMGRSZone zone = new AircraftMGRSZone(1);
        assertEquals(zone.getBytes(), new byte[] {(byte) 0x01});

        // Max
        zone = new AircraftMGRSZone(60);
        assertEquals(zone.getBytes(), new byte[] {(byte) 0x3C});

        zone = new AircraftMGRSZone(34);
        assertEquals(zone.getBytes(), new byte[] {(byte) 0x22});

        assertEquals(zone.getDisplayName(), "Aircraft MGRS Zone");
    }

    @Test
    public void testConstructFromEncoded() {
        // Min
        AircraftMGRSZone zone = new AircraftMGRSZone(new byte[] {(byte) 0x01});
        assertEquals(zone.getZone(), 1);
        assertEquals(zone.getBytes(), new byte[] {(byte) 0x01});
        assertEquals(zone.getDisplayableValue(), "1");

        // Max
        zone = new AircraftMGRSZone(new byte[] {(byte) 0x3C});
        assertEquals(zone.getZone(), 60);
        assertEquals(zone.getBytes(), new byte[] {(byte) 0x3C});
        assertEquals(zone.getDisplayableValue(), "60");

        zone = new AircraftMGRSZone(new byte[] {(byte) 0x22});
        assertEquals(zone.getZone(), 34);
        assertEquals(zone.getBytes(), new byte[] {(byte) 0x22});
        assertEquals(zone.getDisplayableValue(), "34");
        assertEquals(zone.getDisplayName(), "Aircraft MGRS Zone");
    }

    @Test
    public void testFactory() throws KlvParseException {
        byte[] bytes = new byte[] {(byte) 0x23};
        IRvtMetadataValue v = RvtLocalSet.createValue(RvtMetadataKey.MGRSZone, bytes);
        assertTrue(v instanceof AircraftMGRSZone);
        AircraftMGRSZone zone = (AircraftMGRSZone) v;
        assertEquals(zone.getZone(), 35);
        assertEquals(zone.getBytes(), new byte[] {(byte) 0x23});
        assertEquals(zone.getDisplayableValue(), "35");
        assertEquals(zone.getDisplayName(), "Aircraft MGRS Zone");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTooSmall() {
        new AircraftMGRSZone(0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTooBig() {
        new AircraftMGRSZone(61);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void badArrayLength() {
        new AircraftMGRSZone(new byte[] {0x01, 0x02});
    }
}

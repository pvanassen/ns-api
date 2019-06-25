package nl.pvanassen.ns.model.storingen;

import org.junit.Test;

import java.time.LocalDateTime;

import static nl.pvanassen.ns.model.storingen.VerstoringType.WERKZAAMHEID;
import static org.assertj.core.api.Assertions.assertThat;

public class StoringenHandleV2Test {

    @Test
    public void testExampleActualStoringenV2() {
        StoringenHandleV2 handle = new StoringenHandleV2();
        Verstoringen verstoringen = handle.getModel(getClass().getResourceAsStream("/storingen/getDisruptions.json"));
        assertThat(verstoringen).hasSize(4);
        Verstoring verstoring = verstoringen.get(0);
        assertThat(verstoring.getHeader()).isEqualTo("Venlo-Düsseldorf");
        assertThat(verstoring.getOorzaak()).isEqualTo("werkzaamheden");
        assertThat(verstoring.getGevolg()).isEqualTo("Beperkt treinverkeer businzet, extra reistijd 15-30 min.");
        assertThat(verstoring.getBaanvakBeperking()).isEmpty();
        assertThat(verstoring.getBaanvakken()).isEmpty();
        assertThat(verstoring.getExtraReistijd()).isEqualTo("een kwartier tot een half uur");
        assertThat(verstoring.getId()).isEqualTo("2019_vl_dussel_13_23apr");
        assertThat(verstoring.getImpact()).isEqualTo(4);
        assertThat(verstoring.getMaatschappij()).isEqualTo(56);
        assertThat(verstoring.getPeriode()).isEqualTo("tot en met dinsdag 23 april");
        assertThat(verstoring.getPrioriteit()).isEqualTo(20);
        assertThat(verstoring.getGeldigheidsLijst()).hasSize(1);
        assertThat(verstoring.getGeldigheidsLijst().get(0)).hasFieldOrPropertyWithValue("startDatum", LocalDateTime
                .of(2019, 4, 13, 4, 0, 0));
        assertThat(verstoring.getGeldigheidsLijst().get(0)).hasFieldOrPropertyWithValue("eindDatum", LocalDateTime
                .of(2019, 4, 24, 3, 59, 0));
        assertThat(verstoring.getReisadviezen()).isNotNull();
        assertThat(verstoring.getReisadviezen().getTitel()).isEqualTo("U kunt gebruikmaken van de bussen");
        assertThat(verstoring.getReisadviezen().getVerstoringreisadvies()).hasSize(1);
        assertThat(verstoring.getReisadviezen().getVerstoringreisadvies().get(0).getAdvies()).hasSize(2)
                .contains("er rijden bussen tussen Mönchengladbach Hbf en Düsseldorf Hbf",
                "plan uw reis met behulp van de internationale reisplanner");
        assertThat(verstoring.getTrajecten()).hasSize(1);
        assertThat(verstoring.getTrajecten().get(0)).hasFieldOrPropertyWithValue("beginTijd", LocalDateTime
                .of(2019, 4, 13, 4, 0, 0));
        assertThat(verstoring.getTrajecten().get(0)).hasFieldOrPropertyWithValue("eindTijd", LocalDateTime
                .of(2019, 4, 24, 3, 59, 0));
        assertThat(verstoring.getTrajecten().get(0).getStations()).hasSize(7).contains("vl", "kn", "kbry", "kboi", "kdul", "viers", "mcgb");
        assertThat(verstoring.getType()).isEqualTo(WERKZAAMHEID);
    }

}

package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static com.google.common.truth.Truth.assertThat;



/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourRepository service;

    @Before
    public void setup() {
        service = DI.getNewInstanceNeighbourRepository();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours).containsExactlyElementsIn(expectedNeighbours);
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addNeighbourWithSuccess() {
        service.getNeighbours().clear();
        Neighbour addedNeighbour = new Neighbour(1, "Caroline", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint-Pierre-du-Mont ; 5km",
                "+33 6 86 57 90 14", "Bonjour !", false);
        service.createNeighbour(addedNeighbour);
        assertTrue(service.getNeighbours().contains(addedNeighbour));
    }

    @Test
    public void default_neighbourIsNotFavorite(){
       Neighbour neighbour = service.getNeighbourById(4);
       assertFalse(neighbour.getIsFavorite());
    }



    @Test
    public void shouldBeFavoriteIfSetFavorite(){
        Neighbour neighbour = service.getNeighbourById(6);
        service.toggleFavoriteNeighbour(neighbour.getId());
        List<Neighbour> neighbours = service.getFavoriteNeighbours();
        assertTrue(neighbours.contains(neighbour));
    }

    @Test
    public void shouldStillBeFavoriteIfSetFavorite(){
        Neighbour neighbour = service.getNeighbourById(9);
        service.toggleFavoriteNeighbour(neighbour.getId());
        Neighbour sameNeighbour = service.getNeighbourById(9);
        List<Neighbour> neighbours = service.getFavoriteNeighbours();
        assertTrue(neighbours.contains(sameNeighbour));
    }

    @Test
    public void shouldChangeNeighbourStatus(){
        Neighbour neighbour = service.getNeighbourById(3);
        service.toggleFavoriteNeighbour(neighbour.getId());
        List<Neighbour> neighbours = service.getFavoriteNeighbours();
        assertTrue(neighbours.contains(neighbour));
    }

    @Test
    public void shouldGetNeighbourById(){
        int id = 1;
        Neighbour neighbour = service.getNeighbourById(id);
        assertEquals("Caroline", neighbour.getName());
    }

    @Test
    public void favoritesOnly(){
        Neighbour neighbour = service.getNeighbourById(1);
        service.toggleFavoriteNeighbour(neighbour.getId());
        Neighbour neighbour2 = service.getNeighbourById(2);
        service.toggleFavoriteNeighbour(neighbour2.getId());
        List<Neighbour> neighbours = service.getFavoriteNeighbours();
        assertThat(neighbours).containsExactly(
                neighbour,
                neighbour2
        );


    }

}

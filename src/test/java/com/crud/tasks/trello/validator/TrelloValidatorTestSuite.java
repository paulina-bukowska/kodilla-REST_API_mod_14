package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTestSuite {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoards() {
        //Given
        List<TrelloList> xxListOfTrelloLists = new ArrayList<>();
        xxListOfTrelloLists.add(new TrelloList("xx1", "testList1", false));
        xxListOfTrelloLists.add(new TrelloList("xx2", "testList2", true));
        xxListOfTrelloLists.add(new TrelloList("xx3", "testList3", false));
        TrelloBoard trelloBoard1 = new TrelloBoard("xx", "testBoard1", xxListOfTrelloLists);

        List<TrelloList> zzListOfTrelloLists = new ArrayList<>();
        zzListOfTrelloLists.add(new TrelloList("zz1", "testList1", true));
        zzListOfTrelloLists.add(new TrelloList("zz2", "testList2", false));
        zzListOfTrelloLists.add(new TrelloList("zz3", "testList3", false));
        TrelloBoard trelloBoard2 = new TrelloBoard("zz", "testBoard2", zzListOfTrelloLists);

        List<TrelloBoard> listOfTrelloBoards = new ArrayList<>();
        listOfTrelloBoards.add(trelloBoard1);
        listOfTrelloBoards.add(trelloBoard2);

        //When
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(listOfTrelloBoards);

        //Then
        assertEquals(0, filteredBoards.size());

    }
}

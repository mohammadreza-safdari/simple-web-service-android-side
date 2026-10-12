import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.presentation.sportsScreen.SportsEvent
import com.example.presentation.sportsScreen.SportsState
import com.example.presentation.sportsScreen.components.SportItem

@Composable
fun SportsScreen(
    state: SportsState,
    onEvent: (SportsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        OutlinedTextField(
            value = state.sportName,
            onValueChange = {
                onEvent(SportsEvent.NameChanged(it))
            },
            label = { Text("Sport Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.sportDescription,
            onValueChange = {
                onEvent(SportsEvent.DescriptionChanged(it))
            },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                onEvent(SportsEvent.CreateSport)
            }
        ) {
            Text("Create")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.sports) { sport ->
                SportItem(
                    sport = sport,
                    onDelete = {
                        onEvent(SportsEvent.DeleteSport(sport.id))
                    }
                )
            }
        }
        if(state.error != null) {
            Text(state.error)
            val context = LocalContext.current
            Toast.makeText(context, "error:${state.error}", Toast.LENGTH_LONG).show()
        }
    }
}
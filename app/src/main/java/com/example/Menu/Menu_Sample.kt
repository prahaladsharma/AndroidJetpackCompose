## 🧩 Basic Use Case — Drop-down Menu
## ✅ Example: Simple Drop-down with Predefined List

@Composable
fun SimpleDropdownMenu() {
    val options = listOf("Option 1", "Option 2", "Option 3")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(options[0]) }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                modifier = Modifier
                    .clickable { expanded = true }
                    .fillMaxWidth(),
                readOnly = true,
                label = { Text("Select Option") },
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                        contentDescription = "Dropdown Arrow"
                    )
                }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            selectedText = label
                            expanded = false
                        },
                        text = { Text(label) }
                    )
                }
            }
        }
    }
}


## 🔍 Advanced Use Case: Drop-down with ViewModel (MVVM)
## ✅ ViewModel Code
class DropdownViewModel : ViewModel() {
    private val _selectedOption = MutableStateFlow("Select an option")
    val selectedOption: StateFlow<String> = _selectedOption

    val options = listOf("Kotlin", "Java", "Compose", "Flutter")

    fun selectOption(option: String) {
        _selectedOption.value = option
    }
}

## ✅ UI Layer
@Composable
fun DropdownMenuWithViewModel(viewModel: DropdownViewModel = viewModel()) {
    val selected by viewModel.selectedOption.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Technology") },
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ArrowDropDown else Icons.Filled.ArrowDropUp,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            viewModel.options.forEach {
                DropdownMenuItem(
                    onClick = {
                        viewModel.selectOption(it)
                        expanded = false
                    },
                    text = { Text(it) }
                )
            }
        }
    }
}

🧬 Singleton Consideration
If you use a static set of values (like gender or language codes), you might use a Singleton:
    object AppConfig {
        val genderOptions = listOf("Male", "Female", "Other")
    }

    


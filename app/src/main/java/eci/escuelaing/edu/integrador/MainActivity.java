package eci.escuelaing.edu.integrador;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView breedText;
    private DogApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image_dog);
        breedText = findViewById(R.id.text_breed);
        Button randomButton = findViewById(R.id.button_random);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(DogApiService.class);

        randomButton.setOnClickListener(v -> fetchRandomDogImage());
    }

    private void fetchRandomDogImage() {
        service.getRandomDogImage().enqueue(new Callback<DogResponse>() {
            @Override
            public void onResponse(Call<DogResponse> call, Response<DogResponse> response) {
                if(response.isSuccessful()) {
                    DogResponse dogResponse = response.body();
                    // Aquí puedes extraer y mostrar la imagen y la raza
                }
            }

            @Override
            public void onFailure(Call<DogResponse> call, Throwable t) {
                // Manejo de error
            }
        });
    }
}

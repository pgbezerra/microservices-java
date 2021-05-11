package com.pgbezerra.productservice.http;

import com.pgbezerra.productservice.http.data.request.ProductPersistDto;
import com.pgbezerra.productservice.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ProductController {

    @Operation(summary = "Return a product that's corresponding with informed id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"id\": 1,\n" +
                                            "    \"description\": \"PC\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                value = "{\n" +
                                        "    \"errorCode\": \"X_100\",\n" +
                                        "    \"message\": \"Product with id 100 not found\",\n" +
                                        "    \"documentation\": \"/documentation\",\n" +
                                        "    \"path\": \"/products/100\",\n" +
                                        "    \"timestamp\": \"2021-05-10T22:29:07.032074Z\"\n" +
                                        "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_200\",\n" +
                                            "    \"message\": \"Invalid argument\",\n" +
                                            "    \"documentation\": \"/documentation\",\n" +
                                            "    \"path\": \"/products/100a\",\n" +
                                            "    \"timestamp\": \"2021-05-10T22:33:15.456241Z\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_300\",\n" +
                                            "    \"message\": \"Error\",\n" +
                                            "    \"documentation\": \"/documentation\",\n" +
                                            "    \"path\": \"/products/100\",\n" +
                                            "    \"timestamp\": \"2021-05-10T22:36:08.029220Z\"\n" +
                                            "}"
                            )
                    )
            )
    })
    @GetMapping(value = "/{id}")
    Product findById(@PathVariable(value = "id") Long id);

    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"id\": 10,\n" +
                                            "    \"description\": \"Monitor\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_201\",\n" +
                                            "    \"message\": \"Invalid argument\",\n" +
                                            "    \"documentation\": \"/documentation\",\n" +
                                            "    \"path\": \"/products\",\n" +
                                            "    \"timestamp\": \"2021-05-10T22:31:52.843234Z\",\n" +
                                            "    \"errors\": [\n" +
                                            "        {\n" +
                                            "            \"field\": \"description\",\n" +
                                            "            \"message\": \"Product description cannot be empty or null\"\n" +
                                            "        }\n" +
                                            "    ]\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_300\",\n" +
                                            "    \"message\": \"Error\",\n" +
                                            "    \"documentation\": \"/documentation\",\n" +
                                            "    \"path\": \"/products/100\",\n" +
                                            "    \"timestamp\": \"2021-05-10T22:36:08.029220Z\"\n" +
                                            "}"
                            )
                    )
            )
    })
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    Product insert(@RequestBody @Valid ProductPersistDto productPersistDto);

}

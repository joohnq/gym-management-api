package com.joohnq

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase

/**
 * Establishes connection with a MongoDB database.
 *
 * The following configuration properties (in application.yaml/application.conf) can be specified:
 * * `db.mongo.user` username for your database
 * * `db.mongo.password` password for the user
 * * `db.mongo.host` host that will be used for the database connection
 * * `db.mongo.port` port that will be used for the database connection
 * * `db.mongo.maxPoolSize` maximum number of connections to a MongoDB server
 * * `db.mongo.database.name` name of the database
 *
 * IMPORTANT NOTE: in order to make MongoDB connection working, you have to start a MongoDB server first.
 * See the instructions here: https://www.mongodb.com/docs/manual/administration/install-community/
 * all the paramaters above
 *
 * @returns [MongoDatabase] instance
 * */

class MongoClientBuilder {
    private var user: String? = null
    private var password: String? = null
    private var host: String = "127.0.0.1"
    private var port: String = "27017"
    private var maxPoolSize: Int = 20

    fun setUser(user: String?): MongoClientBuilder = apply { this.user = user }
    fun setPassword(password: String?): MongoClientBuilder = apply { this.password = password }
    fun setHost(host: String): MongoClientBuilder = apply { this.host = host }
    fun setPort(port: String): MongoClientBuilder = apply { this.port = port }
    fun setMaxPoolSize(maxPoolSize: Int): MongoClientBuilder = apply { this.maxPoolSize = maxPoolSize }

    fun build(): MongoClient {
        val credentials = user?.let { u -> password?.let { p -> "$u:$p@" } }.orEmpty()
        val uri = "mongodb://$credentials$host:$port/?maxPoolSize=$maxPoolSize&w=majority"

        return MongoClient.create(uri)
    }
}

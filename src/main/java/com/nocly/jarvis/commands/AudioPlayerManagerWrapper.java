package com.nocly.jarvis.commands;


import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer;
import net.dv8tion.jda.api.audio.AudioSendHandler;

public class AudioPlayerManagerWrapper {
    private final AudioPlayerManager playerManager;
    private final AudioPlayer player;
    private AudioSendHandler sendHandler;

    public AudioPlayerManagerWrapper() {
        this.playerManager = new DefaultAudioPlayerManager();
        this.player = playerManager.createPlayer();
        //this.player.setFrameBuffer(new NonAllocatingAudioFrameBuffer());
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
    }

    public AudioPlayer getPlayer() {
        return player;
    }

    public AudioSendHandler getSendHandler() {
        if (sendHandler == null) {
            //sendHandler = new AudioPlayerSendHandler(player);
        }
        return sendHandler;
    }

    public void loadAndPlay(String trackUrl) {
        playerManager.loadItemOrdered(this, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                connectToFirstVoiceChannel();
                play(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                // Handle playlists if needed
            }

            @Override
            public void noMatches() {
                // Notify the user that no tracks were found
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                // Notify the user that loading a track failed
            }
        });
    }

    private void connectToFirstVoiceChannel() {
        // Code to connect to a voice channel
    }

    private void play(AudioTrack track) {
        connectToFirstVoiceChannel();
        player.startTrack(track, false);
    }
}

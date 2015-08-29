/*
 * Licensed to the University of California, Berkeley under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package tachyon.master.next.filesystem.journal;

import java.util.Map;

import com.google.common.collect.Maps;

import tachyon.TachyonURI;
import tachyon.master.next.journal.JournalEntry;
import tachyon.master.next.journal.JournalEntryType;

public class AddCheckpointEntry implements JournalEntry {
  private final long mWorkerId;
  private final long mFileId;
  private final long mLength;
  private final String mCheckpointPath;
  private final long mOpTimeMs;

  public AddCheckpointEntry(long workerId, long fileId, long length, TachyonURI checkpointPath,
      long opTimeMs) {
    mWorkerId = workerId;
    mFileId = fileId;
    mLength = length;
    mCheckpointPath = checkpointPath.getPath();
    mOpTimeMs = opTimeMs;
  }

  public long getWorkerId() {
    return mWorkerId;
  }

  public long getFileId() {
    return mFileId;
  }

  public long getFileLength() {
    return mLength;
  }

  public TachyonURI getCheckpointPath() {
    return new TachyonURI(mCheckpointPath);
  }

  public long getOperationTimeMs() {
    return mOpTimeMs;
  }

  @Override
  public JournalEntryType getType() {
    return JournalEntryType.ADD_CHECKPOINT;
  }

  @Override
  public Map<String, Object> getParameters() {
    Map<String, Object> parameters = Maps.newHashMapWithExpectedSize(5);
    parameters.put("workerId", mWorkerId);
    parameters.put("fileId", mFileId);
    parameters.put("length", mLength);
    parameters.put("checkpointPath", mCheckpointPath);
    parameters.put("operationTimeMs", mOpTimeMs);
    return parameters;
  }
}

package fm.last.commons.test.extensions;

import java.io.File;

import org.junit.jupiter.api.extension.Extension;

import fm.last.commons.test.core.DataFolderUtils;

public class RootSqlFolderExtension extends BaseDataFolder implements Extension {

  private File folder;

  RootSqlFolderExtension() {
    this(new String[] {});
  }

  RootSqlFolderExtension(String... children) {
    folder = DataFolderUtils.buildRootSqlDataFolder(children);
  }

  @Override
  public File getDataFolder() {
    return folder;
  }
}

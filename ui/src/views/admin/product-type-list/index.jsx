import React, { useEffect, useState } from "react";
import DataTable from "components/table/DataTable";
import MasterDataLayout from "components/layout/MasterDataLayout";
import { productService } from "services/productService";
import { MdAdd, MdCategory, MdUpdate, MdOutlineInventory2 } from "react-icons/md";

const columns = [
  { header: "ID", accessorKey: "id" },
  { header: "Name", accessorKey: "name" },
];

export default function ProductTypeList() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    productService.getProductTypes()
      .then(res => setData(res.data || []))
      .catch(err => console.error(err))
      .finally(() => setLoading(false));
  }, []);

  const workspaceActions = [
    {
      label: "Define New Type",
      desc: "Add a new product category",
      icon: <MdAdd className="h-6 w-6" />,
      color: "text-blue-500 bg-blue-50 dark:bg-blue-900/30",
      isPrimary: true,
      onClick: () => alert("Opening Define Type Modal...")
    },
    {
      label: "Category Settings",
      desc: "Configure global rules",
      icon: <MdCategory className="h-5 w-5" />,
      color: "text-purple-500 bg-purple-50 dark:bg-purple-900/30",
      borderColor: "border-l-purple-500",
      onClick: () => alert("Opening Settings...")
    }
  ];

  return (
    <MasterDataLayout title="Workspace" actions={workspaceActions}>
      {loading ? (
        <div className="flex h-64 items-center justify-center">
          <span className="text-gray-400 font-bold animate-pulse uppercase tracking-widest text-xs">Loading Types...</span>
        </div>
      ) : (
        <DataTable columns={columns} data={data} filename="ProductTypes_Export" />
      )}
    </MasterDataLayout>
  );
}
